package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.HasDescriptionProcessor;
import scraper.core.processors.PageProcessor;

public class HasDescriptionScraper extends PropertyScraper {

	public HasDescriptionScraper(PageProcessor pageProcessor) {
		super(
			new HasDescriptionProcessor(pageProcessor)
		);
	}

}
