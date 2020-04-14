package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class StatusScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Current status";

	public StatusScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new StatusProcessor(pageProcessor)
		);
	}

}
