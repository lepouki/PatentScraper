package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.events.EventSource;

import java.util.ArrayList;
import java.util.List;

public class Scraper extends EventSource implements EventListener {

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
		private Document lastDocumentScraped;

		public ScrapingProgress(float progressPercentage, Document lastDocumentScraped) {
			this.progressPercentage = progressPercentage;
			this.lastDocumentScraped = lastDocumentScraped;
		}

		public float getProgressPercentage() {
			return progressPercentage;
		}

		public Document getLastDocumentScraped() {
			return lastDocumentScraped;
		}

	}

	public static Scraper createEmptyScraper() {
		List<Document> documents = new ArrayList<>(0);
		List<ScrapingStep> scrapingSteps = new ArrayList<>(0);
		return new Scraper(documents, scrapingSteps);
	}

	private List<Document> documents;
	private DocumentScraper documentScraper;

	public Scraper(List<Document> documents, List<ScrapingStep> scrapingSteps) {
		setDocuments(documents);
		documentScraper = new DocumentScraper(scrapingSteps);
		documentScraper.pushEventListener(this);
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setScrapingSteps(List<ScrapingStep> scrapingSteps) {
		documentScraper.setScrapingSteps(scrapingSteps);
	}

	public void scrapeDocuments() {
		for (int i = 0; i < documents.size(); ++i) {
			Document document = documents.get(i);
			documentScraper.scrape(document);
			notifyEventListenersDocumentScraped(i, document);
		}
	}

	@Override
	public void eventReceived(Event event) {
		if (event instanceof DocumentScraper.ScrapingStepProgressEvent) {
			notifyEventListeners(event);
		}
	}

	private void notifyEventListenersDocumentScraped(int documentIndex, Document document) {
		float progressPercentage = calculateScrapingProgressPercentage(documentIndex);
		ScrapingProgress scrapingProgress = new ScrapingProgress(progressPercentage, document);
		notifyEventListenersScrapingProgress(scrapingProgress);
	}

	private float calculateScrapingProgressPercentage(int documentIndex) {
		return (float)(documentIndex + 1) / documents.size() * 100.0f;
	}

	private void notifyEventListenersScrapingProgress(ScrapingProgress scrapingProgress) {
		ScrapingProgressEvent event = new ScrapingProgressEvent(this, scrapingProgress);
		notifyEventListeners(event);
	}

}
