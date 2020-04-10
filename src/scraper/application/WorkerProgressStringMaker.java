package scraper.application;

import scraper.core.*;

public class WorkerProgressStringMaker {

	private String currentDocumentIdentifier = "";
	private String currentProperty = "";

	public String makeProgressString() {
		return String.format("%s: %s", currentDocumentIdentifier, currentProperty);
	}

	public void processWorkerProgressEvent(ProgressEvent event) {
		String workerStatus = event.getProgress().getStatus();

		if (event instanceof Worker.ProcessingDocumentEvent) {
			currentDocumentIdentifier = workerStatus;
		}
		else if (event instanceof Scraper.ProcessingPropertyEvent) {
			currentProperty = workerStatus;
		}
	}

}
