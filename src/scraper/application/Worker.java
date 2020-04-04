package scraper.application;

import scraper.core.Document;
import scraper.core.Progress;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;

import javax.swing.*;
import java.util.List;

public class Worker extends SwingWorker<Void, ProgressEvent> implements EventListener {

	public static class DocumentProcessed extends ProgressEvent {

		public DocumentProcessed(Object source, Progress progress) {
			super(source, progress);
		}

	}

	private Application application;
	private Scraper scraper;
	private List<Document> documents;

	public Worker(Application application, Scraper scraper, List<Document> documents) {
		this.application = application;
		this.scraper = scraper;
		this.documents = documents;
		this.scraper.pushEventListener(this);
	}

	@Override
	protected void process(List<ProgressEvent> progressEvents) {
		ProgressEvent lastProgressEvent = progressEvents.get(progressEvents.size() - 1);
		application.onProgressMade(lastProgressEvent);
	}

	@Override
	protected Void doInBackground() {
		for (int i = 0; i < documents.size() && !isCancelled(); ++i) {
			Document document = documents.get(i);
			scraper.scrapeDocument(document);
			notifyApplicationDocumentProcessed(i, document.identifier);
		}

		return null;
	}

	private void notifyApplicationDocumentProcessed(int documentIndex, String documentIdentifier) {
		Progress progress = new Progress(calculateDocumentProgressPercentage(documentIndex), documentIdentifier);

		publish(
			new DocumentProcessed(this, progress)
		);
	}

	private float calculateDocumentProgressPercentage(int documentIndex) {
		return (float)(documentIndex + 1) / documents.size() * 100.0f;
	}

	@Override
	public void onEventReceived(Event event) {
		if (event instanceof Scraper.PropertyProcessed) {
			Scraper.PropertyProcessed propertyProcessed = (Scraper.PropertyProcessed)event;
			publish(propertyProcessed);
		}
	}

}
