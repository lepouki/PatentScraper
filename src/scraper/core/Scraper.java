package scraper.core;

import scraper.application.Worker;
import scraper.core.events.EventSource;

import java.util.*;

public class Scraper extends EventSource {

	public static class LayerProgressEvent extends ProgressEvent {

		public LayerProgressEvent(Object source, int index, int maximumValue, String status) {
			super(source, index, maximumValue, status);
		}

	}

	public static class DocumentProgressEvent extends ProgressEvent {

		public DocumentProgressEvent(Object source, int index, int maximumValue, String status) {
			super(source, index, maximumValue, status);
		}

	}

	private final int layerCount;
	private final ScraperLayerSwapper layerSwapper;
	private final ScraperProgressionCounter progressionCounter;
	private final DocumentScraper documentScraper;

	public Scraper(List<Document> documents, List<PropertyScraper> propertyScrapers, int layerCount) {
		this.layerCount = layerCount;

		layerSwapper = new ScraperLayerSwapper(documents);
		progressionCounter = new ScraperProgressionCounter();
		documentScraper = new DocumentScraper(propertyScrapers);

		updatePropertyScrapers(propertyScrapers);
	}

	private void updatePropertyScrapers(List<PropertyScraper> propertyScrapers) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.setScraper(this);
		}
	}

	public void scrape(Worker worker) {
		resetPropertyScraperSuccessCounts();

		for (int i = 1; i <= layerCount && !worker.isCancelled(); ++i) {
			notifyEventListenersLayerProgress(i);
			scrapeLayer(worker);
			layerSwapper.swapToNextLayerDocuments();
		}
	}

	private void resetPropertyScraperSuccessCounts() {
		documentScraper.resetPropertyScraperSuccessCounts();
	}

	private void notifyEventListenersLayerProgress(int layerIndex) {
		String layerStatus = "Layer " + layerIndex;

		notifyEventListeners(
			new LayerProgressEvent(
				this, layerIndex, layerCount, layerStatus
			)
		);
	}

	private void scrapeLayer(Worker worker) {
		layerSwapper.pushDocumentsToProgressionCounter(progressionCounter);

		for (int i = 0; i < layerSwapper.getDocumentCount() && !worker.isCancelled(); ++i) {
			processDocument(i);
		}
	}

	private void processDocument(int documentIndex) {
		Document document = layerSwapper.getDocument(documentIndex);
		progressionCounter.incrementDocumentCount();
		notifyEventListenersDocumentProgress(documentIndex, document.identifier);
		documentScraper.scrape(document);
	}

	private void notifyEventListenersDocumentProgress(int documentIndex, String documentStatus) {
		int ceilingDocumentIndex = documentIndex + 1;

		notifyEventListeners(
			new DocumentProgressEvent(
				this, ceilingDocumentIndex, layerSwapper.getDocumentCount(), documentStatus
			)
		);
	}

	public void cleanupPropertyScrapers() {
		documentScraper.cleanupPropertyScrapers();
	}

	public void pushNextLayerDocument(Document document) {
		boolean alreadyProcessed = progressionCounter.isDocumentAlreadyProcessed(document);

		if (alreadyProcessed)
			return;

		layerSwapper.pushNextLayerDocument(document);
	}

	public void writeSummary(String filePath) {
		List<PropertyScraper> propertyScrapers = documentScraper.getPropertyScrapers();
		SummaryWriter summaryWriter = new SummaryWriter(progressionCounter.getDocumentCount(), propertyScrapers);
		summaryWriter.writeSummary(filePath);
	}

}
