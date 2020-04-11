package scraper.core;

import scraper.core.events.EventSource;

import java.util.*;

public class Scraper extends EventSource {

	private Set<Document> documents;
	private final DocumentScraper documentScraper;

	public Scraper(Set<Document> documents, List<PropertyScraper> propertyScrapers) {
		setDocument(documents);
		documentScraper = new DocumentScraper(propertyScrapers);
	}

	public void setDocument(Set<Document> documents) {
		this.documents = documents;
	}

	public void scrape() {
		int currentDocument = 0;

		for (Document document : documents) {
			notifyEventListenersProcessingDocument(currentDocument++, document.identifier);
			documentScraper.scrape(document);
		}
	}

	private void notifyEventListenersProcessingDocument(int documentIndex, String documentIdentifier) {
		notifyEventListeners(
			new ProgressEvent(this, calculateDocumentProgressPercentage(documentIndex), documentIdentifier)
		);
	}

	private float calculateDocumentProgressPercentage(int documentIndex) {
		return (float)documentIndex / documents.size() * 100.0f;
	}

}
