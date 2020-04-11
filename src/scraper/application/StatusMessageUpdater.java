package scraper.application;

import scraper.application.groups.ScraperControls;

public class StatusMessageUpdater {

	private final ScraperControls scraperControls;

	public StatusMessageUpdater(ScraperControls scraperControls) {
		this.scraperControls = scraperControls;
	}

	public void handleException(Exception exception) {
		String exceptionMessage = exception.getMessage();
		setMessage("Error: " + exceptionMessage);
	}

	public void setMessage(String message) {
		scraperControls.setStatus(message);
	}

}
