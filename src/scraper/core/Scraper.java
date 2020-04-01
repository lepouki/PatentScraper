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

	public static class ScrapingStepProgressEvent extends Event {

		private ScrapingStepProgress scrapingStepProgress;

		public ScrapingStepProgressEvent(Object source, ScrapingStepProgress scrapingStepProgress) {
			super(source);
			this.scrapingStepProgress = scrapingStepProgress;
		}

		public ScrapingStepProgress getScrapingStepProgress() {
			return scrapingStepProgress;
		}

	}

	public static class ScrapingStepProgress {

		private float progressPercentage;
		private ScrapingStep lastScrapingStepDone;

		public ScrapingStepProgress(float progressPercentage, ScrapingStep lastScrapingStepDone) {
			this.progressPercentage = progressPercentage;
			this.lastScrapingStepDone = lastScrapingStepDone;
		}

		public float getProgressPercentage() {
			return progressPercentage;
		}

		public ScrapingStep getLastScrapingStepDone() {
			return lastScrapingStepDone;
		}

	}

	public static Scraper createEmptyScraper() {
		List<Document> documents = new ArrayList<>(0);
		List<ScrapingStep> scrapingSteps = new ArrayList<>(0);
		return new Scraper(documents, scrapingSteps);
	}

	private List<Document> documents;
	private List<ScrapingStep> scrapingSteps;

	public Scraper(List<Document> documents, List<ScrapingStep> scrapingSteps) {
		setDocuments(documents);
		setScrapingSteps(scrapingSteps);
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setScrapingSteps(List<ScrapingStep> scrapingSteps) {
		this.scrapingSteps = scrapingSteps;
	}

	public void scrapeDocuments() {
		for (int i = 0; i < documents.size(); ++i) {
			Document document = documents.get(i);
			scrapeDocument(document);
			notifyEventListenersDocumentScraped(i, document);
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

	private void scrapeDocument(Document document) {
		for (int i = 0; i < scrapingSteps.size(); ++i) {
			ScrapingStep scrapingStep = scrapingSteps.get(i);
			scrapingStep.writeStepInformation(document);
			notifyEventListenersScrapingStepDone(i, scrapingStep);
		}
	}

	private void notifyEventListenersScrapingStepDone(int scrapingStepIndex, ScrapingStep scrapingStep) {
		float progressPercentage = calculateScrapingStepProgressPercentage(scrapingStepIndex);
		ScrapingStepProgress scrapingStepProgress = new ScrapingStepProgress(progressPercentage, scrapingStep);
		notifyEventListenersScrapingStepProgress(scrapingStepProgress);
	}

	private float calculateScrapingStepProgressPercentage(int scrapingStepIndex) {
		return (float)(scrapingStepIndex + 1) / scrapingSteps.size() * 100.0f;
	}

	private void notifyEventListenersScrapingStepProgress(ScrapingStepProgress scrapingStepProgress) {
		ScrapingStepProgressEvent event = new ScrapingStepProgressEvent(this, scrapingStepProgress);
		notifyEventListeners(event);
	}

}
