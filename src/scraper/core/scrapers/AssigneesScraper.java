package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class AssigneesScraper extends PropertyScraper {

	public AssigneesScraper(PageProcessor pageProcessor) {
		super(
			new AssigneesProcessor(pageProcessor)
		);
	}

}
