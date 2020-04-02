package scraper.loggers;

import scraper.core.DocumentScraper;
import scraper.core.ScrapingStep;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.Logger;

public class ScrapingStepProgressLogger extends Logger implements EventListener {

	@Override
	public void eventReceived(Event event) {
		if (event instanceof DocumentScraper.ScrapingStepProgressEvent) {
			DocumentScraper.ScrapingStepProgressEvent scrapingStepProgressEvent = (DocumentScraper.ScrapingStepProgressEvent)event;
			logScrapingStepProgressEvent(scrapingStepProgressEvent);
		}
	}

	private void logScrapingStepProgressEvent(DocumentScraper.ScrapingStepProgressEvent event) {
		DocumentScraper.ScrapingStepProgress scrapingStepProgress = event.getScrapingStepProgress();
		logScrapingStepProgress(scrapingStepProgress);
	}

	private void logScrapingStepProgress(DocumentScraper.ScrapingStepProgress scrapingStepProgress) {
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
