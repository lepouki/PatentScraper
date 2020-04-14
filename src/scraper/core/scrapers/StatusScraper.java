package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class StatusScraper extends PropertyScraper {

	public StatusScraper(PageProcessor pageProcessor) {
		super(
			new StatusProcessor(pageProcessor)
		);
	}

}
