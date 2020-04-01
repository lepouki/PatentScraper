package scraper.loggers;

import scraper.core.Document;
import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.Logger;

public class ScrapingProgressLogger extends Logger implements EventListener {

	@Override
	public void eventReceived(Event event) {
		if (event instanceof Scraper.ScrapingProgressEvent) {
			Scraper.ScrapingProgressEvent scrapingProgressEvent = (Scraper.ScrapingProgressEvent)event;
			logScrapingProgressEvent(scrapingProgressEvent);
		}
	}

	private void logScrapingProgressEvent(Scraper.ScrapingProgressEvent event) {
		Scraper.ScrapingProgress scrapingProgress = event.getScrapingProgress();
		logScrapingProgress(scrapingProgress);
	}

	private void logScrapingProgress(Scraper.ScrapingProgress scrapingProgress) {
		log("-> Scraping progress: ");

		float progressPercentage = scrapingProgress.getProgressPercentage();
		logProgressPercentage(progressPercentage);

		Document lastDocumentScraped = scrapingProgress.getLastDocumentScraped();
		logLastDocumentScraped(lastDocumentScraped);
	}

	private void logProgressPercentage(float progressPercentage) {
		log("%.2f%%\n", progressPercentage);
	}

	private void logLastDocumentScraped(Document lastDocumentScraped) {
		log("-> Last document scraped: %s\n", lastDocumentScraped.identifier);
	}

}
