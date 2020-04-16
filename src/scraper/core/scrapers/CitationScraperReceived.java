package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.CitationProcessorReceived;
import scraper.core.processors.PageProcessor;

public class CitationScraperReceived extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Citing documents";

	public CitationScraperReceived(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new CitationProcessorReceived(pageProcessor)
		);
	}

}
