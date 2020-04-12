package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.IntegerPicker;
import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.PropertyScraper;
import scraper.core.scrapers.PageScraper;

import java.util.*;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";
	private static final String DATA_FRAME_ADDITIONS_OPTION_GROUP_TITLE = "Data frame additions";
	private static final String CITATIONS_OPTION_GROUP_TITLE = "Citations";
	private static final String ONLINE_PROPERTIES_OPTION_GROUP_TITLE = "Online properties";
	private static final String LAYER_COUNT_PICKER_TITLE = "Layer count";
	private static final int LAYER_COUNT_PICKER_MINIMUM_VALUE = 1;
	private static final int LAYER_COUNT_PICKER_MAXIMUM_VALUE = 10;

	private List<PropertyScraperOptionGroup> propertyScraperOptionGroups;
	private IntegerPicker layerCountPicker;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);


		createOptionGroups();
		createLayerCountPicker();
	}

	private void createOptionGroups() {
		propertyScraperOptionGroups = new ArrayList<>();

		createDataFrameAdditionsOptionGroup();
		createCitationsOptionGroup();
		createOnlinePropertiesOptionGroup();
	}

	private void createDataFrameAdditionsOptionGroup() {
		createOptionGroup(
			DATA_FRAME_ADDITIONS_OPTION_GROUP_TITLE, createDataFrameAdditionsPropertyScrapers()
		);
	}

	private void createOptionGroup(String title, List<PropertyScraper> propertyScrapers) {
		PropertyScraperOptionGroup optionGroup = new PropertyScraperOptionGroup(title, propertyScrapers);
		pushOptionGroup(optionGroup);
	}

	private void pushOptionGroup(PropertyScraperOptionGroup optionGroup) {
		add(optionGroup);
		propertyScraperOptionGroups.add(optionGroup);
	}

	private void createCitationsOptionGroup() {
		createOptionGroup(
			CITATIONS_OPTION_GROUP_TITLE, createCitationsPropertyScrapers()
		);
	}

	private void createOnlinePropertiesOptionGroup() {
		PropertyScraperOptionGroup optionGroup = new PropertyScraperOptionGroup(
			ONLINE_PROPERTIES_OPTION_GROUP_TITLE
		);

		PageScraper pageScraper = new PageScraper();
		optionGroup.pushPreparationPropertyScraper(pageScraper);

		optionGroup.setPropertyScrapers(
			createOnlinePropertiesPropertyScrapers(pageScraper)
		);

		pushOptionGroup(optionGroup);
	}

	private List<PropertyScraper> createDataFrameAdditionsPropertyScrapers() {
		return new ArrayList<>(0);
	}

	private List<PropertyScraper> createCitationsPropertyScrapers() {
		return new ArrayList<>(0);
	}

	private List<PropertyScraper> createOnlinePropertiesPropertyScrapers(PageScraper pageScraper) {
		return new ArrayList<>(0);
	}

	private void createLayerCountPicker() {
		layerCountPicker = new IntegerPicker(
			LAYER_COUNT_PICKER_TITLE,
			LAYER_COUNT_PICKER_MINIMUM_VALUE,
			LAYER_COUNT_PICKER_MAXIMUM_VALUE
		);

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
