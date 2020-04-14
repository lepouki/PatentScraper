package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class TypeScraper extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Document type";

	public TypeScraper(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new TypeProcessor(pageProcessor)
		);
	}

}
