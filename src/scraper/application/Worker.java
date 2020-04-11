package scraper.application;

import scraper.core.*;
import scraper.core.events.Event;

import java.util.*;
import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, Worker.ProgressEvent> {

	public static class ProgressEvent extends Event {

		private final float percentage;
		private final String documentIdentifier;

		public ProgressEvent(Object source, float percentage, String documentIdentifier) {
			super(source);
			this.percentage = percentage;
			this.documentIdentifier = documentIdentifier;
		}

		public float getPercentage() {
			return percentage;
		}

		public String getDocumentIdentifier() {
			return documentIdentifier;
		}

	}

	private final Application application;
	private final Scraper scraper;
	private final Set<Document> documents;

	public Worker(Application application, Scraper scraper, Set<Document> documents) {
		this.application = application;
		this.scraper = scraper;
		this.documents = documents;
	}

	@Override
	protected void process(List<ProgressEvent> progressEvents) {
		boolean isCancelled = isCancelled();
		if (isCancelled) return; // Sometimes process gets called after the worker has been cancelled

		ProgressEvent lastProgressEvent = progressEvents.get(progressEvents.size() - 1);
		application.onWorkerProgressMade(lastProgressEvent);
	}

	@Override
	protected Void doInBackground() {
		int currentDocument = 0;

		for (Document document : documents) {
			notifyApplicationProcessingDocument(currentDocument++, document.identifier);
			scraper.scrapeDocument(document);
		}

		return null;
	}

	private void notifyApplicationProcessingDocument(int documentIndex, String documentIdentifier) {
		publish(
			new ProgressEvent(this, calculateDocumentProgressPercentage(documentIndex), documentIdentifier)
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
