package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class TitleScraper extends PropertyScraper {

	public TitleScraper(PageProcessor pageProcessor) {
		super(
			new TitleProcessor(pageProcessor)
		);
	}

}
