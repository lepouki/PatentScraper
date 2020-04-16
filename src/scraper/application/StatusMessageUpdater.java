package scraper.application;

import scraper.application.groups.ScraperControls;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;

public class StatusMessageUpdater {

	private final ScraperControls scraperControls;
	public StatusMessageUpdater(ScraperControls scraperControls) {
		this.scraperControls = scraperControls;
	}

	public void handleException(Exception exception) {
		String exceptionMessage = exception.getMessage();
		setStatusMessage("Error: " + exceptionMessage);
	}

	public void setStatusMessage(String message) {
		scraperControls.setStatus(message);
	}

}
