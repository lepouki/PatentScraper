package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;
import scraper.core.*;
import scraper.core.scrapers.PageScraper;

import java.util.*;
import javax.swing.*;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";

	private final DataFrameScrapers dataFrameScrapers;
	private final PageScraper pageScraper;
	private final List<PropertyScraperOptionGroup> propertyScraperOptionGroups;

	private LanguageOptionGroup languageOptionGroup;
	private RecursiveScrapingOptionGroup recursiveScrapingOptionGroup;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		propertyScraperOptionGroups = new ArrayList<>();

		dataFrameScrapers = new DataFrameScrapers();
		pageScraper = dataFrameScrapers.getPageScraper();
		createOptionGroups();
	}

	private void createOptionGroups() {
		createLanguageOptionGroup();
		createConcatenateOptionGroup();
		createExtraFilesOptionGroup();
		createRecursiveScrapingOptionGroup();
	}

	private void createConcatenateOptionGroup() {
		ConcatenationOptionGroup concatenationOptionGroup = new ConcatenationOptionGroup(pageScraper);
		pushPropertyScraperOptionGroup(concatenationOptionGroup);
	}

	private void createLanguageOptionGroup() {
		languageOptionGroup = new LanguageOptionGroup();
		pushOptionGroup(languageOptionGroup);
	}

	private void createRecursiveScrapingOptionGroup() {
		recursiveScrapingOptionGroup = new RecursiveScrapingOptionGroup();
		pushOptionGroup(recursiveScrapingOptionGroup);
	}

	private void pushOptionGroup(OptionGroup optionGroup) {
		add(optionGroup);
		createComponentSeparator();
	}

	private void createExtraFilesOptionGroup() {
		ExtraFilesOptionGroup optionGroup = new ExtraFilesOptionGroup(pageScraper);
		pushPropertyScraperOptionGroup(optionGroup);
	}

	private void pushPropertyScraperOptionGroup(PropertyScraperOptionGroup optionGroup) {
		pushOptionGroup(optionGroup);
		propertyScraperOptionGroups.add(optionGroup);
	}

	public List<PropertyScraper> getPropertyScrapers(String outputDirectory) {
		initializePageScraper();

		List<PropertyScraper> propertyScrapers = new ArrayList<>(
			dataFrameScrapers.getPropertyScrapers()
		);

		for (PropertyScraperOptionGroup optionGroup : propertyScraperOptionGroups) {
			propertyScrapers.addAll(
				optionGroup.getPropertyScrapers()
			);
		}

		initializePropertyScrapers(propertyScrapers, outputDirectory);
		return propertyScrapers;
	}

	private void initializePageScraper() {
		boolean useNativeLanguage = languageOptionGroup.useNativeLanguage();
		pageScraper.setUseNativeLanguage(useNativeLanguage);
	}

	private static void initializePropertyScrapers(List<PropertyScraper> propertyScrapers, String outputDirectory) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.initialize(outputDirectory);
		}
	}

	public int getLayerCount() {
		return 1;
	}

}
