package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class AssigneeScraper extends PropertyScraper {

	public AssigneeScraper(PageProcessor pageProcessor) {
		super(
			new AssigneeProcessor(pageProcessor)
		);
	}

}
