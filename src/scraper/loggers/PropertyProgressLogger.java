package scraper.loggers;

import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;

public class PropertyProgressLogger extends ProgressLogger implements EventListener {

	private static final String PREFIX = "--> ";

	public PropertyProgressLogger() {
		super(PREFIX);
	}

	@Override
	public void eventReceived(Event event) {
		if (event instanceof Scraper.PropertyProcessed) {
			Scraper.PropertyProcessed propertyProcessed = (Scraper.PropertyProcessed)event;
			logPropertyProcessed(propertyProcessed);
		}
	}

	private void logPropertyProcessed(Scraper.PropertyProcessed event) {
		Scraper.Progress progress = event.getProgress();
		logPropertyProgress(progress);
	}

	private void logPropertyProgress(Scraper.Progress progress) {
		logPrefix();
		log("Processing properties: ");
		logProgress(progress);
	}

}
