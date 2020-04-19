package scraper.core;

import scraper.core.events.EventSource;

import java.io.IOException;
import java.util.*;

public class Scraper extends EventSource {

	public static class LayerProgressEvent extends ProgressEvent {

		public LayerProgressEvent(Object source, int index, String status) {
			super(source, index, status);
		}

	}

	public static class DocumentProgressEvent extends ProgressEvent {

		public DocumentProgressEvent(Object source, int index, String status) {
			super(source, index, status);
		}

	}

	private final int layerCount;
	private final DocumentScraper documentScraper;

	private List<Document> documents;
	private List<Document> nextLayerDocuments;

	public Scraper(List<Document> documents, List<PropertyScraper> propertyScrapers, int layerCount) {
		updatePropertyScrapers(propertyScrapers);
		this.layerCount = layerCount;

		documentScraper = new DocumentScraper(propertyScrapers);
		copyDocuments(documents);
		nextLayerDocuments = new ArrayList<>();
	}

	private void updatePropertyScrapers(List<PropertyScraper> propertyScrapers) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.setScraper(this);
		}
	}

	public void copyDocuments(List<Document> documents) {
		this.documents = new ArrayList<>(documents);
	}

	public void scrape() {
		for (int i = 1; i <= layerCount; ++i) {
			notifyEventListenersLayerProgress(i);
			scrapeLayer();
			prepareNextLayerDocuments();
		}
	}

	private void notifyEventListenersLayerProgress(int layerIndex) {
		String layerStatus = "Layer " + layerIndex;

		notifyEventListeners(
			new LayerProgressEvent(
				this, calculateLayerProgressPercentage(layerIndex), layerStatus
			)
		);
	}

	private int calculateLayerProgressPercentage(int layerIndex) {
		return (int)(layerIndex / (float)layerCount * 100);
	}

	private void scrapeLayer() {
		int currentDocument = 0;

		for (Document document : documents) {
			notifyEventListenersDocumentProgress(++currentDocument, document.identifier);
			documentScraper.scrape(document);
		}
	}

	private void notifyEventListenersDocumentProgress(int documentIndex, String documentStatus) {
		notifyEventListeners(
			new DocumentProgressEvent(
				this, calculateDocumentProgressPercentage(documentIndex), documentStatus
			)
		);
	}

	private int calculateDocumentProgressPercentage(int documentIndex) {
		return (int)(documentIndex / (float)documents.size() * 100);
	}

	private void prepareNextLayerDocuments() {
		documents = nextLayerDocuments;
		nextLayerDocuments = new ArrayList<>();
	}

	public void cleanupPropertyScrapers() throws IOException {
		documentScraper.cleanupPropertyScrapers();
	}

	public void pushNextLayerDocument(Document document) {
		nextLayerDocuments.add(document);
	}

}
