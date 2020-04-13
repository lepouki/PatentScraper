package scraper.core;

import scraper.core.events.EventSource;

import java.util.*;

public class Scraper extends EventSource {

	public static class LayerProgressEvent extends ProgressEvent {

		public LayerProgressEvent(Object source, float percentage, String status) {
			super(source, percentage, status);
		}

	}

	public static class DocumentProgressEvent extends ProgressEvent {

		public DocumentProgressEvent(Object source, float percentage, String status) {
			super(source, percentage, status);
		}

	}

	private final List<PropertyScraper> propertyScrapers;
	private final int layerCount;
	private final DocumentScraper documentScraper;

	private Set<Document> documents;
	private Set<Document> nextLayerDocuments;
	private long startTime;

	public Scraper(Set<Document> documents, List<PropertyScraper> propertyScrapers, int layerCount) {
		this.propertyScrapers = new ArrayList<>(propertyScrapers);
		updatePropertyScrapers();
		this.layerCount = layerCount;

		documentScraper = new DocumentScraper(propertyScrapers);
		copyDocuments(documents);
		nextLayerDocuments = new HashSet<>();
	}

	private void updatePropertyScrapers() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.setScraper(this);
		}
	}

	public void copyDocuments(Set<Document> documents) {
		this.documents = new HashSet<>(documents);
	}

	public void scrape() {
		startTime = System.nanoTime();

		for (int i = 1; i <= layerCount; ++i) {
			notifyEventListenersLayerProgress(i);
			scrapeLayer();
			prepareNextLayerDocuments();
		}
	}

	private void notifyEventListenersLayerProgress(int layerIndex) {
		String layerStatus = "(layer " + layerIndex + ")";

		notifyEventListeners(
			new LayerProgressEvent(
				this, calculateLayerProgressPercentage(layerIndex), layerStatus
			)
		);
	}

	private float calculateLayerProgressPercentage(int layerIndex) {
		return (float)layerIndex / layerCount * 100.0f;
	}

	private void scrapeLayer() {
		int currentDocument = 0;
		initializePropertyScrapersForNextLayer();

		for (Document document : documents) {
			notifyEventListenersDocumentProgress(++currentDocument, document.identifier);
			documentScraper.scrape(document);
		}
	}

	private void initializePropertyScrapersForNextLayer() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initializeForNextLayer();
		}
	}

	private void notifyEventListenersDocumentProgress(int documentIndex, String documentStatus) {
		notifyEventListeners(
			new DocumentProgressEvent(
				this, calculateDocumentProgressPercentage(documentIndex), documentStatus
			)
		);
	}

	private float calculateDocumentProgressPercentage(int documentIndex) {
		return (float)documentIndex / documents.size() * 100.0f;
	}

	private void prepareNextLayerDocuments() {
		documents = nextLayerDocuments;
		nextLayerDocuments = new HashSet<>();
	}

	public void cleanupPropertyScrapers() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.cleanup();
		}
	}

	public void pushNextLayerDocument(Document document) {
		nextLayerDocuments.add(document);
	}

	public long getNanosecondsElapsed() {
		return System.nanoTime() - startTime;
	}

}
