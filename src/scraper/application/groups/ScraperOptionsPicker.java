package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;
import scraper.core.*;
import scraper.core.processors.PageProcessor;

import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";

	private List<PropertyScraperOptionGroup> optionGroups;
	private PageProcessor pageProcessor;
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
		pageProcessor = optionGroup.getPageProcessor();
	}

	private void pushOptionGroup(PropertyScraperOptionGroup optionGroup) {
		add(optionGroup);
		optionGroups.add(optionGroup);
		createComponentSeparator();
	}

	private void createRecursiveCitationsOptionGroup() {
		RecursiveScrapingOptionGroup optionGroup = new RecursiveScrapingOptionGroup(pageProcessor);
		pushOptionGroup(optionGroup);
	}

	private void createExtraInformationOptionGroup() {
		ExtraInformationOptionGroup optionGroup = new ExtraInformationOptionGroup();
		pushOptionGroup(optionGroup);
	}

	private void createCitationLayerCountPicker() {
		recursionLayerCountPicker = new RecursionLayerCountPicker();
		add(recursionLayerCountPicker);
	}

	public List<PropertyScraper> getPropertyScrapers(String outputDirectory) throws IOException {
		List<PropertyScraper> propertyScrapers = new ArrayList<>();

		for (PropertyScraperOptionGroup optionGroup : optionGroups) {
			propertyScrapers.addAll(
				optionGroup.getPropertyScrapers()
			);
		}

		initializePropertyScrapers(propertyScrapers, outputDirectory);
		return propertyScrapers;
	}

	private static void initializePropertyScrapers(
		List<PropertyScraper> propertyScrapers, String outputDirectory) throws IOException
	{
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initialize(outputDirectory);
		}
	}

	public int getLayerCount() {
		return recursionLayerCountPicker.getValue();
	}

}
