package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;
import scraper.core.*;
import scraper.core.scrapers.PageScraper;

import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";

	private List<PropertyScraperOptionGroup> optionGroups;
	private PageScraper pageScraper;
	private RecursionLayerCountPicker recursionLayerCountPicker;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createOptionGroups();
		createCitationLayerCountPicker();
	}

	private void createOptionGroups() {
		optionGroups = new ArrayList<>();

		createDataFrameAdditionsOptionGroup();
		createExtraInformationOptionGroup();
		createRecursiveCitationsOptionGroup();
	}

	private void createDataFrameAdditionsOptionGroup() {
		DataFrameAdditionsOptionGroup optionGroup = new DataFrameAdditionsOptionGroup();
		pushOptionGroup(optionGroup);
		pageScraper = optionGroup.getPageScraper();
	}

	private void pushOptionGroup(PropertyScraperOptionGroup optionGroup) {
		add(optionGroup);
		optionGroups.add(optionGroup);
		createComponentSeparator();
	}

	private void createRecursiveCitationsOptionGroup() {
		RecursiveScrapingOptionGroup optionGroup = new RecursiveScrapingOptionGroup(pageScraper);
		pushOptionGroup(optionGroup);
	}

	private void createExtraInformationOptionGroup() {
		ExtraInformationOptionGroup optionGroup = new ExtraInformationOptionGroup(pageScraper);
		pushOptionGroup(optionGroup);
	}

	private void createCitationLayerCountPicker() {
		recursionLayerCountPicker = new RecursionLayerCountPicker();
		add(recursionLayerCountPicker);
	}

	public List<PropertyScraper> getPropertyScrapers(String outputDirectory) {
		List<PropertyScraper> propertyScrapers = new ArrayList<>();

		for (PropertyScraperOptionGroup optionGroup : optionGroups) {
			propertyScrapers.addAll(
				optionGroup.getPropertyScrapers()
			);
		}

		initializePropertyScrapers(propertyScrapers, outputDirectory);
		return propertyScrapers;
	}

	private static void initializePropertyScrapers(List<PropertyScraper> propertyScrapers, String outputDirectory)
	{
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initialize(outputDirectory);
		}
	}

	public int getLayerCount() {
		return recursionLayerCountPicker.getValue();
	}

}
