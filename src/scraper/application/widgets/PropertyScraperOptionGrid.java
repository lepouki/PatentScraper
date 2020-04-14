package scraper.application.widgets;

import scraper.application.*;
import scraper.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyScraperOptionGrid extends Widget {

	private List<PropertyScraperOption> propertyScraperOptions;

	public PropertyScraperOptionGrid(List<OptionPropertyScraper> optionPropertyScrapers) {
		GridLayout layout = new GridLayout(0, LayoutConfiguration.OPTIONS_PER_ROW);
		setLayout(layout);

		createPropertyScraperOptions(optionPropertyScrapers);
	}

	private void createPropertyScraperOptions(List<OptionPropertyScraper> optionPropertyScrapers) {
		int optionCount = optionPropertyScrapers.size();
		propertyScraperOptions = new ArrayList<>(optionCount);

		for (OptionPropertyScraper optionPropertyScraper : optionPropertyScrapers) {
			PropertyScraperOption propertyScraperOption = new PropertyScraperOption(optionPropertyScraper);
			add(propertyScraperOption);
			propertyScraperOptions.add(propertyScraperOption);
		}
	}

	public List<PropertyScraper> getPropertyScrapers() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>();

		for (PropertyScraperOption propertyScraperOption : propertyScraperOptions) {
			boolean selected = propertyScraperOption.isSelected();

			if (selected) {
				PropertyScraper propertyScraper = propertyScraperOption.getPropertyScraper();
				propertyScrapers.add(propertyScraper);
			}
		}

		return propertyScrapers;
	}

	public void setPropertyScrapersFileDataWriter(FileDataWriter fileDataWriter) {
		for (PropertyScraperOption propertyScraperOption : propertyScraperOptions) {
			propertyScraperOption.getPropertyScraper().setFileDataWriter(fileDataWriter);
		}
	}

}
