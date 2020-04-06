package scraper.application;

import scraper.core.Document;
import scraper.core.Progress;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;
import scraper.core.events.Event;
import scraper.core.events.EventListener;

import javax.swing.SwingWorker;
import java.util.List;

public class Worker extends SwingWorker<Void, ProgressEvent> implements EventListener {

	public static class ProcessingDocumentEvent extends ProgressEvent {

		public ProcessingDocumentEvent(Object source, Progress progress) {
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
	public void onEventReceived(Event event) {
		if (event instanceof Scraper.ProcessingPropertyEvent) {
			Scraper.ProcessingPropertyEvent processingPropertyEvent = (Scraper.ProcessingPropertyEvent)event;
			publish(processingPropertyEvent);
		}
	}

	@Override
	protected void process(List<ProgressEvent> progressEvents) {
		ProgressEvent lastProgressEvent = progressEvents.get(progressEvents.size() - 1);
		application.onWorkerProgressMade(lastProgressEvent);
	}

	@Override
	protected Void doInBackground() {
		for (int i = 0; i < documents.size() && !isCancelled(); ++i) {
			Document document = documents.get(i);
			notifyApplicationProcessingDocument(i, document.identifier);
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
		return (float)(documentIndex + 1) / documents.size() * 100.0f;
	}

	@Override
	protected void done() {
		application.onWorkerDone();
	}

}
