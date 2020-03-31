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

	private List<Document> documents;
	private List<ScrapingStep> scrapingSteps;

	public Scraper() {
		documents = new ArrayList<>(0);
		scrapingSteps = new ArrayList<>(0);
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
			notifyEventListenersCurrentDocumentDone(i, document);
		}
	}

	private void notifyEventListenersCurrentDocumentDone(int currentDocumentIndex, Document currentDocument) {
		float progressPercentage = (float)currentDocumentIndex / documents.size() * 100.0f;
		ScrapingProgress scrapingProgress = new ScrapingProgress(progressPercentage, currentDocument);
		notifyEventListenersScrapingProgress(scrapingProgress);
	}

	private void notifyEventListenersScrapingProgress(ScrapingProgress scrapingProgress) {
		ScrapingProgressEvent event = new ScrapingProgressEvent(this, scrapingProgress);
		notifyEventListeners(event);
	}

	private void scrapeDocument(Document document) {
		for (int i = 0; i < scrapingSteps.size(); ++i) {
			ScrapingStep scrapingStep = scrapingSteps.get(i);
			scrapingStep.writeDocumentStepInformationToOutputTarget(document);
			notifyEventListenersCurrentScrapingStepDone(i, scrapingStep);
		}
	}

	private void notifyEventListenersCurrentScrapingStepDone(int currentScrapingStepIndex, ScrapingStep currentScrapingStep) {
		float progressPercentage = (float)currentScrapingStepIndex / scrapingSteps.size() * 100.0f;
		ScrapingStepProgress scrapingStepProgress = new ScrapingStepProgress(progressPercentage, currentScrapingStep);
		notifyEventListenersScrapingStepProgress(scrapingStepProgress);
	}

	private void notifyEventListenersScrapingStepProgress(ScrapingStepProgress scrapingStepProgress) {
		ScrapingStepProgressEvent event = new ScrapingStepProgressEvent(this, scrapingStepProgress);
		notifyEventListeners(event);
	}

}
