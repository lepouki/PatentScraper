package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.CitationCountProcessorReceived;
import scraper.core.processors.PageProcessor;

public class CitationCountScraperReceived extends PropertyScraper {

	public CitationCountScraperReceived(PageProcessor pageProcessor) {
		super(
			new CitationCountProcessorReceived(pageProcessor)
		);
	}

}
