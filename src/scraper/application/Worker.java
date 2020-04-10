package scraper.application;

import scraper.core.Document;
import scraper.core.Progress;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;

import javax.swing.SwingWorker;
import java.util.List;
import java.util.Set;

public class Worker extends SwingWorker<Void, ProgressEvent> implements EventListener {

	public static class ProcessingDocumentEvent extends ProgressEvent {

		public ProcessingDocumentEvent(Object source, Progress progress) {
			super(source, progress);
		}

	}

	private final Application application;
	private final Scraper scraper;
	private final Set<Document> documents;

	public Worker(Application application, Scraper scraper, Set<Document> documents) {
		this.application = application;
		this.scraper = scraper;
		this.documents = documents;
		this.scraper.pushEventListener(this);
	}

	@Override
	public void onEventReceived(Event event) {
		Scraper.ProcessingPropertyEvent processingPropertyEvent = (Scraper.ProcessingPropertyEvent)event;
		publish(processingPropertyEvent);
	}

	@Override
	protected void process(List<ProgressEvent> progressEvents) {
		boolean isCancelled = isCancelled();
		if (isCancelled) return; // Sometimes process gets called after the worker has been cancelled

		for (ProgressEvent progressEvent : progressEvents) {
			application.onWorkerProgressMade(progressEvent);
		}
	}

	@Override
	protected Void doInBackground() {
		int currentDocument = 0;

		for (Document document : documents) {
			notifyApplicationProcessingDocument(currentDocument, document.identifier);
			scraper.scrapeDocument(document);
		}

		return null;
	}

	private void notifyApplicationProcessingDocument(int documentIndex, String documentIdentifier) {
		Progress progress = new Progress(calculateDocumentProgressPercentage(documentIndex), documentIdentifier);

		publish(
			new ProcessingDocumentEvent(this, progress)
		);
	}

	private float calculateDocumentProgressPercentage(int documentIndex) {
		return (float)documentIndex / documents.size() * 100.0f;
	}

	@Override
	protected void done() {
		application.onWorkerDone();
	}

}
