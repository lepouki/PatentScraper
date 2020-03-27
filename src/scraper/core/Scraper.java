package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventSource;
import scraper.core.options.Options;

import java.util.List;

public class Scraper extends EventSource {

	public static class StartScrapingDocumentEvent extends Event {

		public StartScrapingDocumentEvent(Object source, Document document) {
			super(source);
			this.document = document;
		}

		public Document getDocument() {
			return document;
		}

		private Document document;

	}

	public Scraper(Options options, List<Document> documents) {
		setOptions(options);
		setDocuments(documents);
	}

	public void setOptions(Options options) { this.options = options; }

	public void setDocuments(List<Document> documents) { this.documents = documents; }

	public void scrapeDocuments() {
		for (Document document : documents) {
			notifyListenersStartScrapingDocument(document);
			scrapeSingleDocument(document);
		}
	}

	private void scrapeSingleDocument(Document document) {
	}

	private void notifyListenersStartScrapingDocument(Document document) {
		StartScrapingDocumentEvent event = new StartScrapingDocumentEvent(this, document);
		notifyEventListeners(event);
	}

	private Options options;
	private List<Document> documents;

}
