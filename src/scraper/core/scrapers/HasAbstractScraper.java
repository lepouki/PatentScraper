package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class HasAbstractScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Has abstract";

	public HasAbstractScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new HasAbstractProcessor(pageProcessor)
		);
	}

}
