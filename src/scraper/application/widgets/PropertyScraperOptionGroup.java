package scraper.application.widgets;

import scraper.application.Widget;
import scraper.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PropertyScraperOptionGroup extends Widget {

	private PropertyScraperOptionGrid propertyScraperOptionGrid;

	// Preparation property scrapers are not visible in the option grid
	// They get run before the other property scrapers do
	private final List<PropertyScraper> preparationPropertyScrapers;

	public PropertyScraperOptionGroup(String title) {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		preparationPropertyScrapers = new ArrayList<>();
		createTitleLabel(title);
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		makeTitleTextBold(titleLabel);
		add(titleLabel, BorderLayout.NORTH);
	}

	private void makeTitleTextBold(JLabel titleLabel) {
		Font titleFont = titleLabel.getFont();

		titleLabel.setFont(
			titleFont.deriveFont(titleFont.getStyle() | Font.BOLD)
		);
	}

	public void setOptionPropertyScrapers(List<PropertyScraper> optionPropertyScraper) {
		if (propertyScraperOptionGrid != null)
			return; // Ignore if the option grid has already been created

		propertyScraperOptionGrid = new PropertyScraperOptionGrid(optionPropertyScraper);
		add(propertyScraperOptionGrid, BorderLayout.SOUTH);
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
