package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class HasAbstractScraper extends PropertyScraper {

	public HasAbstractScraper(PageProcessor pageProcessor) {
		super(
			new HasAbstractProcessor(pageProcessor)
		);
	}

}
