package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class PublicationDateScraper extends PropertyScraper {

	public PublicationDateScraper(PageProcessor pageProcessor) {
		super(
			new PublicationDateProcessor(pageProcessor)
		);
	}

}
