package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.*;
import scraper.core.processors.PageProcessor;
import scraper.core.scrapers.*;
import scraper.core.writers.CsvFileDataWriter;

import java.util.*;

public class RecursiveScrapingOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Scrape documents recursively";

	private final CsvFileDataWriter citationFileDataWriter;

	public RecursiveScrapingOptionGroup(PageProcessor pageProcessor) {
		super(TITLE);

		citationFileDataWriter = new CsvFileDataWriter();

		createPreparationPropertyScrapers();
		createPropertyScrapers(pageProcessor);

		setPropertyScrapersFileDataWriter(citationFileDataWriter);
	}

	private void createPreparationPropertyScrapers() {
		List<PropertyScraper> preparationScrapers = new ArrayList<>();

		preparationScrapers.add(
			new CitationFileDataWriterCreator()
		);

		pushPreparationPropertyScrapers(preparationScrapers);
	}

	private void createPropertyScrapers(PageProcessor pageProcessor) {
		List<OptionPropertyScraper> citationsScrapers = new ArrayList<>();

		citationsScrapers.add(
			new CitationScraperGiven(pageProcessor)
		);

		citationsScrapers.add(
			new CitationScraperReceived(pageProcessor)
		);

		setOptionPropertyScrapers(citationsScrapers);
	}

	@Override
	public void setOptionPropertyScrapers(List<OptionPropertyScraper> citationsScrapers) {
		setFileDataWriterColumns(citationsScrapers);
		super.setOptionPropertyScrapers(citationsScrapers);
	}

	private void setFileDataWriterColumns(List<OptionPropertyScraper> citationsScrapers) {
		String[] columnNames = citationsScrapers.get(0).getPropertyNames();

		citationFileDataWriter.setColumnNames(
			Arrays.asList(columnNames)
		);
	}

}
