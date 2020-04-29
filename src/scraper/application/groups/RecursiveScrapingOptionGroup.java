package scraper.application.groups;

import scraper.application.widgets.*;
import scraper.core.*;
import scraper.core.scrapers.*;

import java.util.*;

public class RecursiveScrapingOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Scrape recursively";

	public RecursiveScrapingOptionGroup(PageScraper pageScraper) {
		super(TITLE);
		createPropertyScrapers(pageScraper);
	}

	private void createPropertyScrapers(PageScraper pageScraper) {
		List<PropertyScraper> citationScrapers = new ArrayList<>();

		citationScrapers.add(
			new CitationScraperGiven(pageScraper)
		);

		citationScrapers.add(
			new CitationScraperNonPatent(pageScraper)
		);

		citationScrapers.add(
			new CitationScraperReceived(pageScraper)
		);

		citationScrapers.add(
			new SimilarDocumentsScraper(pageScraper)
		);

		setOptionPropertyScrapers(citationScrapers);
	}

}
