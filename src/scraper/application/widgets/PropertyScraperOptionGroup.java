package scraper.application.widgets;

import scraper.core.*;

import java.util.*;

public class PropertyScraperOptionGroup extends OptionGroup {

	private PropertyScraperOptionGrid propertyScraperOptionGrid;

	// Preparation property scrapers are not visible in the option grid
	// They get run before the other property scrapers do
	private final List<PropertyScraper> preparationPropertyScrapers;

	public PropertyScraperOptionGroup(String title) {
		super(title);
		preparationPropertyScrapers = new ArrayList<>();
	}

	public void setOptionPropertyScrapers(List<PropertyScraper> optionPropertyScraper) {
		if (propertyScraperOptionGrid != null)
			return; // Ignore if the option grid has already been created

		propertyScraperOptionGrid = new PropertyScraperOptionGrid(optionPropertyScraper);
		setContent(propertyScraperOptionGrid);
	}

	public void pushPreparationPropertyScrapers(List<PropertyScraper> propertyScrapers) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			pushPreparationPropertyScraper(propertyScraper);
		}
	}

	public void pushPreparationPropertyScraper(PropertyScraper propertyScraper) {
		preparationPropertyScrapers.add(propertyScraper);
	}

	public List<PropertyScraper> getPropertyScrapers() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>(preparationPropertyScrapers);

		propertyScrapers.addAll(
			propertyScraperOptionGrid.getPropertyScrapers()
		);

		return propertyScrapers;
	}

	public void setPropertyScrapersFileDataWriter(FileWriter fileWriter) {
		for (PropertyScraper propertyScraper : preparationPropertyScrapers) {
			propertyScraper.setFileWriter(fileWriter);
		}

		propertyScraperOptionGrid.setPropertyScrapersFileDataWriter(fileWriter);
	}

}
