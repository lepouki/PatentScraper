package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.*;
import scraper.core.scrapers.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public class RecursiveScrapingOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Scrape documents recursively";

	private final CsvFileWriter citationFileWriter;

	public RecursiveScrapingOptionGroup(PageScraper pageScraper) {
		super(TITLE);

		citationFileWriter = new CsvFileWriter();
		setCitationFileWriterColumnNames();

		createOptionPropertyScrapers(pageScraper);
		setPropertyScrapersFileDataWriter(citationFileWriter);
	}

	private void setCitationFileWriterColumnNames() {
		List<String> columnNames = Arrays.asList(
			CitationScraper.getColumnNames()
		);

		citationFileWriter.setColumnNames(columnNames);
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
