package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class GrantDateScraper extends PropertyScraper {

	public GrantDateScraper(PageProcessor pageProcessor) {
		super(
			new GrantDateProcessor(pageProcessor)
		);
	}

}
