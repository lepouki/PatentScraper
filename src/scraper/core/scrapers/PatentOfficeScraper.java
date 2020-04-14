package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class PatentOfficeScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Patent office";

	public PatentOfficeScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new PatentOfficeProcessor(pageProcessor)
		);
	}

}
