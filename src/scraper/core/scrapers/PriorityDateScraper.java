package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class PriorityDateScraper extends PropertyScraper {

	public PriorityDateScraper(PageProcessor pageProcessor) {
		super(
			new PriorityDateProcessor(pageProcessor)
		);
	}

}
