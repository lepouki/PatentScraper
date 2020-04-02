package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventSource;

import java.util.List;

public class DocumentScraper extends EventSource {

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

	private List<ScrapingStep> scrapingSteps;

	public DocumentScraper(List<ScrapingStep> scrapingSteps) {
		setScrapingSteps(scrapingSteps);
	}

	public void setScrapingSteps(List<ScrapingStep> scrapingSteps) {
		this.scrapingSteps = scrapingSteps;
	}

	public void scrape(Document document) {
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
