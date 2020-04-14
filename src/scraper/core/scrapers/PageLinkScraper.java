package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class PageLinkScraper extends PropertyScraper {

	public PageLinkScraper(PageProcessor pageProcessor) {
		super(
			new PageLinkProcessor(pageProcessor)
		);
	}

}
