package scraper.application.widgets;

import scraper.core.PropertyScraper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PropertyScraperOptionGroup extends JPanel {

	private PropertyScraperOptionGrid propertyScraperOptionGrid;

	// Preparation property scrapers are not visible in the option grid
	// They get run before the other property scrapers do
	private final List<PropertyScraper> preparationPropertyScrapers;

	public PropertyScraperOptionGroup(String title, List<PropertyScraper> propertyScrapers) {
		this(title);
		setPropertyScrapers(propertyScrapers);
	}

	public void setPropertyScrapers(List<PropertyScraper> propertyScrapers) {
		if (propertyScraperOptionGrid != null) return; // Ignore if the option grid has already been created
		propertyScraperOptionGrid = new PropertyScraperOptionGrid(propertyScrapers);
		add(propertyScraperOptionGrid);
	}

	public PropertyScraperOptionGroup(String title) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		preparationPropertyScrapers = new ArrayList<>();
		createTitleLabel(title);
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		add(titleLabel);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

}
