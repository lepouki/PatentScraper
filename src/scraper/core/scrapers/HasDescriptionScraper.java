package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class HasDescriptionScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Has description";

	public HasDescriptionScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new HasDescriptionProcessor(pageProcessor)
		);
	}

}
