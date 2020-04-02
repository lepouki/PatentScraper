package scraper.loggers;

import scraper.core.Progress;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.Logger;

public class ScraperProgressLogger extends Logger implements EventListener {

	@Override
	public void eventReceived(Event event) {
		if (event.getSource() instanceof Scraper) {
			ProgressEvent scraperProgressEvent = (ProgressEvent)event;
			logScraperProgressEvent(scraperProgressEvent);
		}
	}

	private void logScraperProgressEvent(ProgressEvent scraperProgressEvent) {
		Progress scraperProgress = scraperProgressEvent.getProgress();
		logScraperProgress(scraperProgress);
	}

	private void logScraperProgress(Progress scraperProgress) {
		log("-> Scraper progress: ");

		float percentage = scraperProgress.getPercentage();
		logPercentage(percentage);

		String lastDocumentIdentifier = scraperProgress.getLastItemProcessed();
		logLastDocumentIdentifier(lastDocumentIdentifier);
	}

	private void logPercentage(float progressPercentage) {
		log("%.2f%%\n", progressPercentage);
	}

	private void logLastDocumentIdentifier(String lastDocumentIdentifier) {
		log("-> Last document identifier: %s\n", lastDocumentIdentifier);
	}

}
