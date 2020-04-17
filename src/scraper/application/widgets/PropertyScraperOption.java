package scraper.application.widgets;

import scraper.core.*;

import javax.swing.*;

public class PropertyScraperOption extends JCheckBox {

	private final PropertyScraper propertyScraper;

	public PropertyScraperOption(PropertyScraper propertyScraper) {
		this.propertyScraper = propertyScraper;
		String propertyName = this.propertyScraper.getReadableName();
		setText(propertyName);
	}

	public PropertyScraper getPropertyScraper() {
		return propertyScraper;
	}

}
