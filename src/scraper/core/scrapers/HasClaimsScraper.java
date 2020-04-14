package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class HasClaimsScraper extends PropertyScraper {

	public HasClaimsScraper(PageProcessor pageProcessor) {
		super(
			new HasClaimsProcessor(pageProcessor)
		);
	}

}
