package scraper.application.widgets;

import scraper.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyScraperOptionGroup extends OptionGroup {

	private final List<PropertyScraper> preparationPropertyScrapers;
	private PropertyScraperOptionGrid propertyScraperOptionGrid;

	public PropertyScraperOptionGroup(String title) {
		super(title);
		preparationPropertyScrapers = new ArrayList<>();
	}

	public void setOptionPropertyScrapers(List<PropertyScraper> optionPropertyScraper) {
		if (propertyScraperOptionGrid != null)
			return; // Ignore if the option grid has already been created

		propertyScraperOptionGrid = new PropertyScraperOptionGrid(optionPropertyScraper);
		add(propertyScraperOptionGrid, BorderLayout.CENTER);
	}

	public void pushPreparationPropertyScraper(PropertyScraper propertyScraper) {
		preparationPropertyScrapers.add(propertyScraper);
	}

	public List<PropertyScraper> getPropertyScrapers() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>(preparationPropertyScrapers);
		pushOptionPropertyScrapersToPropertyScrapers(propertyScrapers);
		return propertyScrapers;
	}

	private void pushOptionPropertyScrapersToPropertyScrapers(List<PropertyScraper> propertyScrapers) {
		if (propertyScraperOptionGrid == null)
			return;

		propertyScrapers.addAll(
			propertyScraperOptionGrid.getPropertyScrapers()
		);
	}

}
