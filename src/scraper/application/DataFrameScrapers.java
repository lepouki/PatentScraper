package scraper.application;

import scraper.core.PropertyScraper;
import scraper.core.scrapers.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public class DataFrameScrapers {

	private final List<PropertyScraper> propertyScrapers;
	private final PageScraper pageScraper;
	private final CsvFileWriter dataFrameWriter;

	public DataFrameScrapers() {
		propertyScrapers = new ArrayList<>();
		pageScraper = new PageScraper();
		dataFrameWriter = new CsvFileWriter();

		createPropertyScrapers();
		setPropertyScrapersFileWriter();
	}

	public PageScraper getPageScraper() {
		return pageScraper;
	}

	private void createPropertyScrapers() {
		propertyScrapers.add(pageScraper);

		propertyScrapers.add(
			new IdentifierScraper(pageScraper)
		);

		propertyScrapers.add(
			new TitleScraper(pageScraper)
		);

		propertyScrapers.add(
			new AssigneeScraper(pageScraper)
		);

		propertyScrapers.add(
			new InventorOrAuthorScraper(pageScraper)
		);

		propertyScrapers.add(
			new PriorityDateScraper(pageScraper)
		);

		propertyScrapers.add(
			new FilingOrCreationDateScraper(pageScraper)
		);

		propertyScrapers.add(
			new PublicationDateScraper(pageScraper)
		);

		propertyScrapers.add(
			new GrantDateScraper(pageScraper)
		);

		propertyScrapers.add(
			new PageLinkScraper(pageScraper)
		);

		propertyScrapers.add(
			new PatentOfficeScraper(pageScraper)
		);

		propertyScrapers.add(
			new TypeScraper(pageScraper)
		);

		propertyScrapers.add(
			new StatusScraper(pageScraper)
		);

		propertyScrapers.add(
			new HasAbstractScraper(pageScraper)
		);

		propertyScrapers.add(
			new HasDescriptionScraper(pageScraper)
		);

		propertyScrapers.add(
			new HasClaimsScraper(pageScraper)
		);

		propertyScrapers.add(
			new ClaimCountScraper(pageScraper)
		);

		propertyScrapers.add(
			new CitationCountScraperReceived(pageScraper)
		);

		propertyScrapers.add(
			new CitationCountScraperGiven(pageScraper)
		);

		propertyScrapers.add(
			new CitationCountScraperNonPatent(pageScraper)
		);

		propertyScrapers.add(
			new GenderWithProbabilityScraper(pageScraper)
		);

		setDataFrameWriterColumnNames();
	}

	private void setDataFrameWriterColumnNames() {
		List<String> columnNames = getDataFrameColumnNames();
		dataFrameWriter.setColumnNames(columnNames);
	}

	private List<String> getDataFrameColumnNames() {
		List<String> columnNames = new ArrayList<>();

		for (PropertyScraper propertyScraper : propertyScrapers) {
			String[] propertyNames = propertyScraper.getPropertyNames();

			columnNames.addAll(
				Arrays.asList(propertyNames)
			);
		}

		return columnNames;
	}

	private void setPropertyScrapersFileWriter() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.setFileWriter(dataFrameWriter);
		}
	}

	public List<PropertyScraper> getPropertyScrapers() {
		return propertyScrapers;
	}

}
