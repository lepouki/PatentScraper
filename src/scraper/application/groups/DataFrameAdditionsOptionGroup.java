package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.*;
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
		createOptionPropertyScrapers();

		setPropertyScrapersFileDataWriter(dataFrameWriter);
	}

	private void createPreparationPropertyScrapers() {
		List<PropertyScraper> preparationScrapers = new ArrayList<>();

		preparationScrapers.add(
			new IdentifierScraper(pageScraper)
		);

		preparationScrapers.add(
			new TitleScraper(pageScraper)
		);

		preparationScrapers.add(
			new AssigneesScraper(pageScraper)
		);

		preparationScrapers.add(
			new InventorOrAuthorScraper(pageScraper)
		);

		preparationScrapers.add(
			new PriorityDateScraper(pageScraper)
		);

		preparationScrapers.add(
			new FilingOrCreationDateScraper(pageScraper)
		);

		preparationScrapers.add(
			new PublicationDateScraper(pageScraper)
		);

		preparationScrapers.add(
			new GrantDateScraper(pageScraper)
		);

		preparationScrapers.add(
			new PageLinkScraper(pageScraper)
		);

		pushPreparationPropertyScrapers(preparationScrapers);
	}

	private void createOptionPropertyScrapers() {
		List<PropertyScraper> additionsScrapers = new ArrayList<>();

		additionsScrapers.add(
			new PatentOfficeScraper(pageScraper)
		);

		additionsScrapers.add(
			new TypeScraper(pageScraper)
		);

		additionsScrapers.add(
			new StatusScraper(pageScraper)
		);

		additionsScrapers.add(
			new HasAbstractScraper(pageScraper)
		);

		additionsScrapers.add(
			new HasDescriptionScraper(pageScraper)
		);

		additionsScrapers.add(
			new HasClaimsScraper(pageScraper)
		);

		additionsScrapers.add(
			new ClaimCountScraper(pageScraper)
		);

		additionsScrapers.add(
			new CitationCountScraperReceived(pageScraper)
		);

		additionsScrapers.add(
			new CitationCountScraperGiven(pageScraper)
		);

		additionsScrapers.add(
			new CitationCountScraperNonPatent(pageScraper)
		);

		additionsScrapers.add(
			new GenderWithProbabilityScraper(pageScraper)
		);

		additionsScrapers.add(
			new IntermediateClassificationScraper(pageScraper)
		);

		setOptionPropertyScrapers(additionsScrapers);
	}

	@Override
	public List<PropertyScraper> getPropertyScrapers() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>();
		propertyScrapers.add(pageScraper);

		propertyScrapers.addAll(
			super.getPropertyScrapers()
		);

		setDataFrameColumnNames(propertyScrapers);
		return propertyScrapers;
	}

	private void setDataFrameColumnNames(List<PropertyScraper> propertyScrapers) {
		List<String> columnNames = getDataFrameColumnNames(propertyScrapers);
		dataFrameWriter.setColumnNames(columnNames);
	}

	private List<String> getDataFrameColumnNames(List<PropertyScraper> propertyScrapers) {
		List<String> columnNames = new ArrayList<>();

		for (PropertyScraper propertyScraper : propertyScrapers) {
			pushPropertyNamesToColumnNames(propertyScraper, columnNames);
		}

		return columnNames;
	}

	private void pushPropertyNamesToColumnNames(PropertyScraper propertyScraper, List<String> columnNames) {
		String[] propertyNames = propertyScraper.getPropertyNames();

		columnNames.addAll(
			Arrays.asList(propertyNames)
		);
	}

	@Override
	public void setOptionPropertyScrapers(List<PropertyScraper> optionPropertyScrapers) {
		super.setOptionPropertyScrapers(optionPropertyScrapers);

	}

	public PageScraper getPageScraper() {
		return pageScraper;
	}

}