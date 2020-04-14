package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class ClaimCountScraper extends PropertyScraper {

	public ClaimCountScraper(PageProcessor pageProcessor) {
		super(
			new ClaimCountProcessor(pageProcessor)
		);
	}

}
