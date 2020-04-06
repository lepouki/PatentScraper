package scraper.application.widgets;

import scraper.application.ScraperOptions;

public class OptionsPicker extends Group {

	private static final String TITLE = "Options";

	public OptionsPicker() {
		super(TITLE);
	}

	public ScraperOptions getScraperOptions() {
		return new ScraperOptions();
	}

}
