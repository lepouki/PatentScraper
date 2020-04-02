package scraper.loggers;

import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;

public class DocumentProgressLogger extends ProgressLogger implements EventListener {

	private static final String PREFIX = "-> ";

	public DocumentProgressLogger() {
		super(PREFIX);
	}

	@Override
	public void eventReceived(Event event) {
		if (event instanceof Scraper.DocumentProcessed) {
			Scraper.DocumentProcessed documentProcessed = (Scraper.DocumentProcessed)event;
			logDocumentProcessed(documentProcessed);
		}
	}

	private void logDocumentProcessed(Scraper.DocumentProcessed event) {
		Scraper.Progress progress = event.getProgress();
		logDocumentProgress(progress);
	}

	private void logDocumentProgress(Scraper.Progress progress) {
		logPrefix();
		log("Processing documents: ");
		logProgress(progress);
	}

}
