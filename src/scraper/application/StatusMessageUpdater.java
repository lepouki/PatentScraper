package scraper.application;

import scraper.application.groups.ScraperControls;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;

public class StatusMessageUpdater {

	private final ScraperControls scraperControls;
	private String layerStatus;
	private String documentStatus;

	public StatusMessageUpdater(ScraperControls scraperControls) {
		this.scraperControls = scraperControls;
		layerStatus = "";
		documentStatus = "";
	}

	public void handleException(Exception exception) {
		String exceptionMessage = exception.getMessage();
		setStatusMessage("Error: " + exceptionMessage);
	}

	public void handleProgressEvent(ProgressEvent event) {
		String status = event.getStatus();

		if (event instanceof Scraper.LayerProgressEvent) {
			layerStatus = status;
		}
		else if (event instanceof Scraper.DocumentProgressEvent) {
			documentStatus = status;
		}

		updateProgressStatusMessage();
	}

	private void updateProgressStatusMessage() {
		setStatusMessage(documentStatus + ' ' + layerStatus);
	}

	public void setStatusMessage(String message) {
		scraperControls.setStatus(message);
	}

}
