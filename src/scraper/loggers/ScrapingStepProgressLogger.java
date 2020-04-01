package scraper.loggers;

import scraper.core.Scraper;
import scraper.core.ScrapingStep;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.Logger;

public class ScrapingStepProgressLogger extends Logger implements EventListener {

	@Override
	public void eventReceived(Event event) {
		if (event instanceof Scraper.ScrapingStepProgressEvent) {
			Scraper.ScrapingStepProgressEvent scrapingStepProgressEvent = (Scraper.ScrapingStepProgressEvent)event;
			logScrapingStepProgressEvent(scrapingStepProgressEvent);
		}
	}

	private void logScrapingStepProgressEvent(Scraper.ScrapingStepProgressEvent event) {
		Scraper.ScrapingStepProgress scrapingStepProgress = event.getScrapingStepProgress();
		logScrapingStepProgress(scrapingStepProgress);
	}

	private void logScrapingStepProgress(Scraper.ScrapingStepProgress scrapingStepProgress) {
		log("--> Scraping step progress: ");

		float progressPercentage = scrapingStepProgress.getProgressPercentage();
		logProgressPercentage(progressPercentage);

		ScrapingStep lastScrapingStepDone = scrapingStepProgress.getLastScrapingStepDone();
		logLastScrapingStepDone(lastScrapingStepDone);
	}

	private void logProgressPercentage(float progressPercentage) {
		log("%.2f%%\n", progressPercentage);
	}

	private void logLastScrapingStepDone(ScrapingStep lastScrapingStepDone) {
		String lastScrapingStepDoneDescription = lastScrapingStepDone.getDescription();
		log("--> Last scraping step done: %s\n", lastScrapingStepDoneDescription);
	}

}
