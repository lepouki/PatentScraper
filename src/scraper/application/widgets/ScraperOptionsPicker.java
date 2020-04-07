package scraper.application.widgets;

import scraper.application.ScraperOptions;

public class ScraperOptionsPicker extends Group {

	private static final String TITLE = "Options";

	public ScraperOptionsPicker(int padding) {
		super(TITLE, padding);
	}

	public ScraperOptions getScraperOptions() {
		return new ScraperOptions();
	}

}
