package scraper.application.widgets;

import scraper.core.PropertyScraper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PropertyScraperOptionGroup extends JPanel {

	private final List<PropertyScraper> preparationPropertyScrapers;
	private final PropertyScraperOptionGrid propertyScraperOptionGrid;

	public PropertyScraperOptionGroup(String title, List<PropertyScraper> propertyScrapers) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		preparationPropertyScrapers = new ArrayList<>();

		createTitleLabel(title);
		propertyScraperOptionGrid = new PropertyScraperOptionGrid(propertyScrapers);
		add(propertyScraperOptionGrid);
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		add(titleLabel);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public void pushPreparationPropertyScraper(PropertyScraper propertyScraper) {
		preparationPropertyScrapers.add(propertyScraper);
	}

	public List<PropertyScraper> getPropertyScrapers() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>(preparationPropertyScrapers);

		List<PropertyScraper> optionPropertyScrapers = propertyScraperOptionGrid.getPropertyScrapers();
		propertyScrapers.addAll(optionPropertyScrapers);

		return propertyScrapers;
	}

}
