package scraper.application.widgets;

import scraper.application.ScraperOptions;

public class OptionsPicker extends Group {

	private static final String NAME = "Options";

	public OptionsPicker() {
		super(NAME);
	}

	public ScraperOptions getScraperOptions() {
		return new ScraperOptions();
	}

}
