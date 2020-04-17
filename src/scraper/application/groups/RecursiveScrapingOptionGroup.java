package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.*;
import scraper.core.scrapers.*;
import scraper.core.writers.CsvFileDataWriter;

import java.util.*;

public class RecursiveScrapingOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Scrape documents recursively";

	private final CsvFileDataWriter citationFileDataWriter;

	public RecursiveScrapingOptionGroup(PageScraper pageScraper) {
		super(TITLE);

		citationFileDataWriter = new CsvFileDataWriter();

		createPreparationPropertyScrapers();
		createOptionPropertyScrapers(pageScraper);

		setPropertyScrapersFileDataWriter(citationFileDataWriter);
	}

	private void createPreparationPropertyScrapers() {
		List<PropertyScraper> preparationScrapers = new ArrayList<>();

		preparationScrapers.add(
			new CitationFileDataWriterCreator()
		);

		pushPreparationPropertyScrapers(preparationScrapers);
	}

	private void createOptionPropertyScrapers(PageScraper pageScraper) {
		List<PropertyScraper> citationsScrapers = new ArrayList<>();

		citationsScrapers.add(
			new CitationScraperGiven(pageScraper)
		);

		citationsScrapers.add(
			new CitationScraperReceived(pageScraper)
		);

		setOptionPropertyScrapers(citationsScrapers);
	}

	@Override
	public void setOptionPropertyScrapers(List<PropertyScraper> citationsScrapers) {
		setFileDataWriterColumns(citationsScrapers);
		super.setOptionPropertyScrapers(citationsScrapers);
	}

	private void setFileDataWriterColumns(List<PropertyScraper> citationsScrapers) {
		String[] columnNames = citationsScrapers.get(0).getPropertyNames();

		citationFileDataWriter.setColumnNames(
			Arrays.asList(columnNames)
		);
	}

}
