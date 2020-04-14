package scraper.core.scrapers;

import scraper.core.OptionPropertyScraper;
import scraper.core.processors.*;

public class CitationCountScraperReceived extends OptionPropertyScraper {

	private static final String OPTION_NAME = "Received citation count";

	public CitationCountScraperReceived(PageProcessor pageProcessor) {
		super(
			OPTION_NAME,
			new CitationCountProcessorReceived(pageProcessor)
		);
	}

}
