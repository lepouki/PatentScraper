package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class ClaimCountScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Claim count";

	public ClaimCountScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new ClaimCountProcessor(pageProcessor)
		);
	}

}
