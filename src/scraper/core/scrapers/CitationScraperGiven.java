package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class CitationScraperGiven extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Cited documents";

	public CitationScraperGiven(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new CitationProcessorGiven(pageProcessor)
		);
	}

}
