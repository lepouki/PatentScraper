package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class InventorOrAuthorScraper extends PropertyScraper {

	public InventorOrAuthorScraper(PageProcessor pageProcessor) {
		super(
			new InventorOrAuthorProcessor(pageProcessor)
		);
	}

}
