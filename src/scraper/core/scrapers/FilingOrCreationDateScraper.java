package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class FilingOrCreationDateScraper extends PropertyScraper {

	public FilingOrCreationDateScraper(PageProcessor pageProcessor) {
		super(
			new FilingOrCreationDateProcessor(pageProcessor)
		);
	}

}
