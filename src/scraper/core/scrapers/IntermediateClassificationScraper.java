package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class IntermediateClassificationScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Intermediate classification";

	public IntermediateClassificationScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new IntermediateClassificationProcessor(pageProcessor)
		);
	}

}
