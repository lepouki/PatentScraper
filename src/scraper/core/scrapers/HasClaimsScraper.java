package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class HasClaimsScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Has claims";

	public HasClaimsScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new HasClaimsProcessor(pageProcessor)
		);
	}

}
