package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class GenderScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Gender with probability";

	public GenderScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new GenderWithProbabilityProcessor(pageProcessor)
		);
	}

}
