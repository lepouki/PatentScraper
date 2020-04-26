package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;
import scraper.core.*;
import scraper.core.scrapers.PageScraper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";
	private static final String NATIVE_LANGUAGE_CHECKBOX_TEXT = "Use native document language";

	private JCheckBox nativeLanguageCheckbox;
	private List<PropertyScraperOptionGroup> optionGroups;
	private RecursionLayerCountPicker recursionLayerCountPicker;
	private PageScraper pageScraper;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createNativeLanguageCheckbox();
		createOptionGroups();
		createCitationLayerCountPicker();
	}

	private void createNativeLanguageCheckbox() {
		nativeLanguageCheckbox = new JCheckBox(NATIVE_LANGUAGE_CHECKBOX_TEXT);
		add(nativeLanguageCheckbox);
		nativeLanguageCheckbox.setAlignmentX(Component.CENTER_ALIGNMENT);
		createComponentSeparator();
	}

	private void createOptionGroups() {
		optionGroups = new ArrayList<>();
		createDataFrameAdditionsOptionGroup();
		createExtraFilesOptionGroup();
		createRecursiveScrapingOptionGroup();
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

	private void createRecursiveScrapingOptionGroup() {
		RecursiveScrapingOptionGroup optionGroup = new RecursiveScrapingOptionGroup(pageScraper);
		pushOptionGroup(optionGroup);
	}

	private void createExtraFilesOptionGroup() {
		ExtraFilesOptionGroup optionGroup = new ExtraFilesOptionGroup(pageScraper);
		pushOptionGroup(optionGroup);
	}

	private void createCitationLayerCountPicker() {
		recursionLayerCountPicker = new RecursionLayerCountPicker();
		add(recursionLayerCountPicker);
	}

	public List<PropertyScraper> getPropertyScrapers(String outputDirectory) {
		initializePageScraper();
		List<PropertyScraper> propertyScrapers = new ArrayList<>();

		for (PropertyScraperOptionGroup optionGroup : optionGroups) {
			propertyScrapers.addAll(
				optionGroup.getPropertyScrapers()
			);
		}

		initializePropertyScrapers(propertyScrapers, outputDirectory);
		return propertyScrapers;
	}

	private void initializePageScraper() {
		boolean useNativeLanguage = nativeLanguageCheckbox.isSelected();
		pageScraper.setUseNativeLanguage(useNativeLanguage);
	}

	private static void initializePropertyScrapers(List<PropertyScraper> propertyScrapers, String outputDirectory) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initialize(outputDirectory);
		}
	}

	public int getLayerCount() {
		return recursionLayerCountPicker.getValue();
	}

}
