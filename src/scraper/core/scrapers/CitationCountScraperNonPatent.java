package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class CitationCountScraperNonPatent extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Non patent citation count";

	public CitationCountScraperNonPatent(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new CitationCountProcessorNonPatent(pageProcessor)
		);
	}

}
