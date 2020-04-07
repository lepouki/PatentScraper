package scraper.application.widgets;

import scraper.application.ScraperOptions;

public class OptionsPicker extends Group {

	private static final String TITLE = "Options";

	public OptionsPicker(int padding) {
		super(TITLE, padding);
	}

	public ScraperOptions getScraperOptions() {
		return new ScraperOptions();
	}

}
