package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class GenderScraper extends PropertyScraper {

	public GenderScraper(PageProcessor pageProcessor) {
		super(
			new GenderProcessor(pageProcessor)
		);
	}

}
