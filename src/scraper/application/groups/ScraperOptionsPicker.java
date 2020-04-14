package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;
import scraper.core.*;

import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";

	private List<PropertyScraperOptionGroup> optionGroups;
	private LayerCountPicker layerCountPicker;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);


		createOptionGroups();
		createLayerCountPicker();
	}

	private void createOptionGroups() {
		optionGroups = new ArrayList<>();

		createDataFrameAdditionsOptionGroup();
		createCitationsOptionGroup();
		createExtraInformationOptionGroup();
	}

	private void createDataFrameAdditionsOptionGroup() {
		PropertyScraperOptionGroup optionGroup = new DataFrameAdditionsOptionGroup();
		pushOptionGroup(optionGroup);
	}

	private void pushOptionGroup(PropertyScraperOptionGroup optionGroup) {
		add(optionGroup);
		optionGroups.add(optionGroup);
	}

	private void createCitationsOptionGroup() {
		PropertyScraperOptionGroup optionGroup = new CitationsOptionGroup();
		pushOptionGroup(optionGroup);
	}

	private void createExtraInformationOptionGroup() {
		PropertyScraperOptionGroup optionGroup = new ExtraInformationOptionGroup();
		pushOptionGroup(optionGroup);
	}

	private void createLayerCountPicker() {
		layerCountPicker = new LayerCountPicker();
		add(layerCountPicker);
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

	private static void initializePropertyScrapers(List<PropertyScraper> propertyScrapers, String outputDirectory) throws IOException {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initialize(outputDirectory);
		}
	}

	public int getLayerCount() {
		return layerCountPicker.getValue();
	}

}
