package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class PatentOfficeScraper extends PropertyScraper {

	public PatentOfficeScraper(PageProcessor pageProcessor) {
		super(
			new PatentOfficeProcessor(pageProcessor)
		);
	}

}
