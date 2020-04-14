package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.OptionPropertyScraper;
import scraper.core.PropertyScraper;
import scraper.core.processors.GenderWithProbabilityProcessor;
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
			new IdentifierScraper(pageProcessor)
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
		List<OptionPropertyScraper> additionsScrapers = new ArrayList<>();
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

		additionsScrapers.add(
			new GenderScraper(pageProcessor)
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
	public void setOptionPropertyScrapers(List<OptionPropertyScraper> optionPropertyScrapers) {
		super.setOptionPropertyScrapers(optionPropertyScrapers);

	}

	public PageProcessor getPageProcessor() {
		return (PageProcessor)pageScraper.getPropertyProcessor();
	}

}
