package scraper.application;

import scraper.application.groups.ScraperControls;

public class ExceptionHandler {

	private final ScraperControls scraperControls;

	public ExceptionHandler(ScraperControls scraperControls) {
		this.scraperControls = scraperControls;
	}

	public void handle(Exception exception) {
		String exceptionMessage = exception.getMessage();
		scraperControls.setStatusText("Error: " + exceptionMessage);
	}

}
