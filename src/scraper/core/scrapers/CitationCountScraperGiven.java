package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class CitationCountScraperGiven extends PropertyScraper {

	public CitationCountScraperGiven(PageProcessor pageProcessor) {
		super(
			new CitationCountProcessorGiven(pageProcessor)
		);
	}

}
