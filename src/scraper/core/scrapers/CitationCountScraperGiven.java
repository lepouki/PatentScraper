package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class CitationCountScraperGiven extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Given citation count";

	public CitationCountScraperGiven(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new CitationCountProcessorGiven(pageProcessor)
		);
	}

}
