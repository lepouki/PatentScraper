package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventSource;

import java.util.ArrayList;
import java.util.List;

public class Scraper extends EventSource {

	public static class ScrapingProgressEvent extends Event {

		private ScrapingProgress scrapingProgress;

		public ScrapingProgressEvent(Object source, ScrapingProgress scrapingProgress) {
			super(source);
			this.scrapingProgress = scrapingProgress;
		}

		public ScrapingProgress getScrapingProgress() {
			return scrapingProgress;
		}

	}

	public static class ScrapingProgress {

		private float progressPercentage;
		private Document documentBeingScraped;

		public ScrapingProgress(float progressPercentage, Document documentBeingScraped) {
			this.progressPercentage = progressPercentage;
			this.documentBeingScraped = documentBeingScraped;
		}

		public float getProgressPercentage() {
			return progressPercentage;
		}

		public Document getDocumentBeingScraped() {
			return documentBeingScraped;
		}

	}

	private List<Document> documents;

	public Scraper() {
		documents = new ArrayList<>();
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void scrapeDocuments() {
		for (int i = 0; i < documents.size(); ++i) {
			Document document = documents.get(i);
			notifyEventListenersCurrentDocumentChanged(i, document);
			scrapeDocument(document);
		}
	}

	private void notifyEventListenersCurrentDocumentChanged(int currentDocumentIndex, Document currentDocument) {
		float progressPercentage = (float)currentDocumentIndex / documents.size() * 100.0f;
		ScrapingProgress scrapingProgress = new ScrapingProgress(progressPercentage, currentDocument);
		notifyEventListenersScrapingProgress(scrapingProgress);
	}

	private void notifyEventListenersScrapingProgress(ScrapingProgress scrapingProgress) {
		ScrapingProgressEvent event = new ScrapingProgressEvent(this, scrapingProgress);
		notifyEventListeners(event);
	}

	private void scrapeDocument(Document document) {
	}

}
