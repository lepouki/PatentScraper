package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.PropertyScraper;
import scraper.core.processors.GenderProcessor;
import scraper.core.processors.PageProcessor;
import scraper.core.scrapers.*;
import scraper.core.writers.CsvFileDataWriter;

import java.util.*;

public class DataFrameAdditionsOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Data frame additions";

	private final CsvFileDataWriter dataFrameWriter;
	private final PageScraper pageScraper;

	public DataFrameAdditionsOptionGroup() {
		super(TITLE);
		dataFrameWriter = new CsvFileDataWriter();
		pageScraper = new PageScraper();

		createPreparationPropertyScrapers();
		createPropertyScrapers();

		setPropertyScrapersFileDataWriter(dataFrameWriter);
	}

	private void createPreparationPropertyScrapers() {
		List<PropertyScraper> preparationScrapers = new ArrayList<>();
		PageProcessor pageProcessor = (PageProcessor)pageScraper.getPropertyProcessor();

		preparationScrapers.add(
			new IdentifierScraper()
		);

		preparationScrapers.add(
			new TitleScraper(pageProcessor)
		);

		preparationScrapers.add(
			new AssigneeScraper(pageProcessor)
		);

		preparationScrapers.add(
			new InventorOrAuthorScraper(pageProcessor)
		);

		preparationScrapers.add(
			new PriorityDateScraper(pageProcessor)
		);

		preparationScrapers.add(
			new FilingOrCreationDateScraper(pageProcessor)
		);

		preparationScrapers.add(
			new PublicationDateScraper(pageProcessor)
		);

		preparationScrapers.add(
			new GrantDateScraper(pageProcessor)
		);

		preparationScrapers.add(
			new PageLinkScraper(pageProcessor)
		);

		pushPreparationPropertyScrapers(preparationScrapers);
	}

	private void createPropertyScrapers() {
		List<PropertyScraper> additionsScrapers = new ArrayList<>();
		PageProcessor pageProcessor = (PageProcessor)pageScraper.getPropertyProcessor();

		additionsScrapers.add(
			new PatentOfficeScraper(pageProcessor)
		);

		additionsScrapers.add(
			new TypeScraper(pageProcessor)
		);

		additionsScrapers.add(
			new StatusScraper(pageProcessor)
		);

		additionsScrapers.add(
			new HasAbstractScraper(pageProcessor)
		);

		additionsScrapers.add(
			new HasDescriptionScraper(pageProcessor)
		);

		additionsScrapers.add(
			new HasClaimsScraper(pageProcessor)
		);

		additionsScrapers.add(
			new ClaimCountScraper(pageProcessor)
		);

		additionsScrapers.add(
			new CitationCountScraperReceived(pageProcessor)
		);

		additionsScrapers.add(
			new CitationCountScraperGiven(pageProcessor)
		);

		additionsScrapers.add(
			new CitationCountScraperNonPatent(pageProcessor)
		);

		GenderScraper genderScraper = new GenderScraper(pageProcessor);
		additionsScrapers.add(genderScraper);

		additionsScrapers.add(
			new GenderProbabilityScraper(
				(GenderProcessor)genderScraper.getPropertyProcessor()
			)
		);

		setPropertyScrapers(additionsScrapers);
	}

	@Override
	public List<PropertyScraper> getPropertyScrapers() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>();
		propertyScrapers.add(pageScraper);

		List<PropertyScraper> dataFramePropertyScrapers = super.getPropertyScrapers();
		propertyScrapers.addAll(dataFramePropertyScrapers);

		setDataFrameColumnNames(dataFramePropertyScrapers);
		return propertyScrapers;
	}

	private void setDataFrameColumnNames(List<PropertyScraper> propertyScrapers) {
		List<String> columnNames = getDataFrameColumnNames(propertyScrapers);
		dataFrameWriter.setColumnNames(columnNames);
	}

	private List<String> getDataFrameColumnNames(List<PropertyScraper> propertyScrapers) {
		int columnCount = propertyScrapers.size();
		List<String> columnNames = new ArrayList<>(columnCount);

		for (PropertyScraper propertyScraper : propertyScrapers) {
			columnNames.add(
				propertyScraper.getPropertyName()
			);
		}

		return columnNames;
	}

	@Override
	public void setPropertyScrapers(List<PropertyScraper> propertyScrapers) {
		super.setPropertyScrapers(propertyScrapers);

	}

	public PageProcessor getPageProcessor() {
		return (PageProcessor)pageScraper.getPropertyProcessor();
	}

}
