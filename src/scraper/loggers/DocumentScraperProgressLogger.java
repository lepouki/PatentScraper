package scraper.loggers;

import scraper.core.DocumentScraper;
import scraper.core.Progress;
import scraper.core.ProgressEvent;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.Logger;

public class DocumentScraperProgressLogger extends Logger implements EventListener {

	@Override
	public void eventReceived(Event event) {
		if (event.getSource() instanceof DocumentScraper) {
			ProgressEvent documentScraperProgressEvent = (ProgressEvent)event;
			logDocumentScraperProgressEvent(documentScraperProgressEvent);
		}
	}

	private void logDocumentScraperProgressEvent(ProgressEvent documentScraperProgressEvent) {
		Progress documentScraperProgress = documentScraperProgressEvent.getProgress();
		logDocumentScraperProgress(documentScraperProgress);
	}

	private void logDocumentScraperProgress(Progress documentScraperProgress) {
		log("--> Document scraper progress: ");

		float percentage = documentScraperProgress.getPercentage();
		logPercentage(percentage);

		String lastProperty = documentScraperProgress.getLastItemProcessed();
		logLastProperty(lastProperty);
	}

	private void logPercentage(float progressPercentage) {
		log("%.2f%%\n", progressPercentage);
	}

	private void logLastProperty(String lastProperty) {
		log("--> Last property: %s\n", lastProperty);
	}

}
