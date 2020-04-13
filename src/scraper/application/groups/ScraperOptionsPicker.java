package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;
import scraper.core.*;
import scraper.core.processors.PageProcessor;
import scraper.core.scrapers.*;
import scraper.core.writers.*;

import java.util.*;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";
	private static final String DATA_FRAME_ADDITIONS_OPTION_GROUP_TITLE = "Data frame additions";
	private static final String CITATIONS_OPTION_GROUP_TITLE = "Citations";
	private static final String ONLINE_PROPERTIES_OPTION_GROUP_TITLE = "Online properties";

	private List<PropertyScraperOptionGroup> propertyScraperOptionGroups;
	private LayerCountPicker layerCountPicker;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		PageScraper pageScraper = new PageScraper();
		createOptionGroups(pageScraper);

		createLayerCountPicker();
	}

	private void createOptionGroups(PageScraper pageScraper) {
		propertyScraperOptionGroups = new ArrayList<>();

		createDataFrameAdditionsOptionGroup(pageScraper);
		createCitationsOptionGroup(pageScraper);
		createOnlinePropertiesOptionGroup(pageScraper);
	}

	private void createDataFrameAdditionsOptionGroup(PageScraper pageScraper) {
		PropertyScraperOptionGroup optionGroup = new PropertyScraperOptionGroup(
			DATA_FRAME_ADDITIONS_OPTION_GROUP_TITLE
		);

		optionGroup.pushPreparationPropertyScraper(pageScraper);
		CsvFileDataWriter dataFrameWriter = new CsvFileDataWriter();

		List<PropertyScraper> preparationScrapers = createDataFramePreparationPropertyScrapers(dataFrameWriter, pageScraper);
		optionGroup.pushPreparationPropertyScrapers(preparationScrapers);

		List<PropertyScraper> additionsScrapers = createDataFrameAdditionsPropertyScrapers(dataFrameWriter, pageScraper);
		optionGroup.setPropertyScrapers(additionsScrapers);

		dataFrameWriter.setColumnNames(
			createDataFrameColumnNames(preparationScrapers, additionsScrapers)
		);

		pushOptionGroup(optionGroup);
	}

	private List<PropertyScraper> createDataFramePreparationPropertyScrapers(FileDataWriter dataFrameWriter, PageScraper pageScraper) {
		List<PropertyScraper> preparationScrapers = new ArrayList<>();
		PageProcessor pageProcessor = (PageProcessor)pageScraper.getPropertyProcessor();

		preparationScrapers.add(
			new IdentifierScraper(dataFrameWriter)
		);

		preparationScrapers.add(
			new TitleScraper(dataFrameWriter, pageProcessor)
		);

		preparationScrapers.add(
			new CurrentAssigneeScraper(dataFrameWriter, pageProcessor)
		);

		preparationScrapers.add(
			new OriginalAssigneeScraper(dataFrameWriter, pageProcessor)
		);

		preparationScrapers.add(
			new InventorOrAuthorScraper(dataFrameWriter, pageProcessor)
		);

		preparationScrapers.add(
			new PriorityDateScraper(dataFrameWriter, pageProcessor)
		);

		preparationScrapers.add(
			new PageLinkScraper(dataFrameWriter, pageProcessor)
		);

		return preparationScrapers;
	}

	private List<PropertyScraper> createDataFrameAdditionsPropertyScrapers(FileDataWriter dataFrameWriter, PageScraper pageScraper) {
		return new ArrayList<>(0);
	}

	private List<String> createDataFrameColumnNames(List<PropertyScraper> preparationScrapers, List<PropertyScraper> additionsScrapers) {
		List<PropertyScraper> propertyScrapers = combinePropertyScrapers(preparationScrapers, additionsScrapers);

		int columnCount = propertyScrapers.size();
		List<String> columnNames = new ArrayList<>(columnCount);

		for (PropertyScraper propertyScraper : propertyScrapers) {
			columnNames.add(
				propertyScraper.getPropertyName()
			);
		}

		return columnNames;
	}

	private List<PropertyScraper> combinePropertyScrapers(List<PropertyScraper> preparationScrapers, List<PropertyScraper> propertyScrapers) {
		List<PropertyScraper> combined = new ArrayList<>(preparationScrapers);
		combined.addAll(propertyScrapers);
		return combined;
	}

	private void createOptionGroup(String title, List<PropertyScraper> propertyScrapers) {
		PropertyScraperOptionGroup optionGroup = new PropertyScraperOptionGroup(title, propertyScrapers);
		pushOptionGroup(optionGroup);
	}

	private void pushOptionGroup(PropertyScraperOptionGroup optionGroup) {
		add(optionGroup);
		propertyScraperOptionGroups.add(optionGroup);
	}

	private void createCitationsOptionGroup(PageScraper pageScraper) {
		createOptionGroup(
			CITATIONS_OPTION_GROUP_TITLE, createCitationsPropertyScrapers()
		);
	}

	private List<PropertyScraper> createCitationsPropertyScrapers() {
		return new ArrayList<>(0);
	}

	private void createOnlinePropertiesOptionGroup(PageScraper pageScraper) {
		PropertyScraperOptionGroup optionGroup = new PropertyScraperOptionGroup(
			ONLINE_PROPERTIES_OPTION_GROUP_TITLE
		);

		optionGroup.setPropertyScrapers(
			getOnlinePropertiesPropertyScrapers(pageScraper)
		);

		pushOptionGroup(optionGroup);
	}

	private List<PropertyScraper> getOnlinePropertiesPropertyScrapers(PageScraper pageScraper) {
		return new ArrayList<>(0);
	}

	private void createLayerCountPicker() {
		layerCountPicker = new LayerCountPicker();
		add(layerCountPicker);
	}

	public List<PropertyScraper> getPropertyScrapers(String outputDirectory) {
		List<PropertyScraper> propertyScrapers = new ArrayList<>();

		for (PropertyScraperOptionGroup propertyScraperOptionGroup : propertyScraperOptionGroups) {
			List<PropertyScraper> optionGroupPropertyScrapers = propertyScraperOptionGroup.getPropertyScrapers();
			propertyScrapers.addAll(optionGroupPropertyScrapers);
			initializePropertyScrapers(optionGroupPropertyScrapers, outputDirectory);
		}

		return propertyScrapers;
	}

	private static void initializePropertyScrapers(List<PropertyScraper> propertyScrapers, String outputDirectory) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initialize(outputDirectory);
		}
	}

	public int getLayerCount() {
		return layerCountPicker.getValue();
	}

}
