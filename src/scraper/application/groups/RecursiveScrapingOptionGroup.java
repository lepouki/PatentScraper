package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.*;
import scraper.core.scrapers.*;

import java.util.*;

public class RecursiveScrapingOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Process documents recursively";

	public RecursiveScrapingOptionGroup(PageScraper pageScraper) {
		super(TITLE);
		createOptionPropertyScrapers(pageScraper);
	}

	private void createOptionPropertyScrapers(PageScraper pageScraper) {
		List<PropertyScraper> citationsScrapers = new ArrayList<>();

		citationsScrapers.add(
			new CitationScraperGiven(pageScraper)
		);

		citationsScrapers.add(
			new CitationScraperReceived(pageScraper)
		);

		citationsScrapers.add(
			new SimilarDocumentsScraper(pageScraper)
		);

		setOptionPropertyScrapers(citationsScrapers);
	}

}
