package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.events.EventSource;

import java.util.ArrayList;
import java.util.List;

public class Scraper extends EventSource implements EventListener {

	public static class Progress extends scraper.core.Progress {

		private String lastDocumentIdentifier;

		public Progress(float percentage, String lastDocumentIdentifier) {
			super(percentage);
			this.lastDocumentIdentifier = lastDocumentIdentifier;
		}

		@Override
		public String getLastItemProcessed() {
			return lastDocumentIdentifier;
		}

	}

	public static Scraper createEmptyScraper() {
		List<Document> documents = new ArrayList<>(0);
		List<PropertyWriter> propertyWriters = new ArrayList<>(0);
		return new Scraper(documents, propertyWriters);
	}

	private List<Document> documents;
	private DocumentScraper documentScraper;

	public Scraper(List<Document> documents, List<PropertyWriter> propertyWriters) {
		setDocuments(documents);
		documentScraper = new DocumentScraper(propertyWriters);
		documentScraper.pushEventListener(this);
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setPropertyWriters(List<PropertyWriter> propertyWriters) {
		documentScraper.setPropertyWriters(propertyWriters);
	}

	public void scrapeDocuments() {
		for (int i = 0; i < documents.size(); ++i) {
			Document document = documents.get(i);
			documentScraper.scrapeDocumentProperties(document);
			notifyEventListenersDocumentScraped(i, document);
		}
	}

	@Override
	public void eventReceived(Event event) {
		if (event.getSource() instanceof DocumentScraper) {
			notifyEventListeners(event);
		}
	}

	private void notifyEventListenersDocumentScraped(int documentIndex, Document document) {
		Progress progress = new Progress(calculateProgressPercentage(documentIndex), document.identifier);
		notifyEventListenersProgress(progress);
	}

	private float calculateProgressPercentage(int documentIndex) {
		return (float)(documentIndex + 1) / documents.size() * 100.0f;
	}

	private void notifyEventListenersProgress(Progress progress) {
		ProgressEvent event = new ProgressEvent(this, progress);
		notifyEventListeners(event);
	}

}
