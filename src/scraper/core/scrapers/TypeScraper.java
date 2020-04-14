package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class TypeScraper extends PropertyScraper {

	public TypeScraper(PageProcessor pageProcessor) {
		super(
			new TypeProcessor(pageProcessor)
		);
	}

}
