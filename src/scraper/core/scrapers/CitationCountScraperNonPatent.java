package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class CitationCountScraperNonPatent extends PropertyScraper {

	public CitationCountScraperNonPatent(PageProcessor pageProcessor) {
		super(
			new CitationCountProcessorNonPatent(pageProcessor)
		);
	}

}
