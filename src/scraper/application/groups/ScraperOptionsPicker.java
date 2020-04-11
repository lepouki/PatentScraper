package scraper.application.groups;

import scraper.application.*;
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

	private WorkerManager workerManager;
	private List<PropertyScraperOptionGroup> propertyScraperOptionGroups;

	public ScraperOptionsPicker(WorkerManager workerManager) {
		super(TITLE, LayoutConfiguration.PADDING);

		this.workerManager = workerManager;

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createOptionGroups();
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

}
