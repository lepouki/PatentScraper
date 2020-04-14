package scraper.application.widgets;

import scraper.core.*;

import javax.swing.*;

public class PropertyScraperOption extends JCheckBox {

	private final OptionPropertyScraper propertyScraper;

	public PropertyScraperOption(OptionPropertyScraper propertyScraper) {
		this.propertyScraper = propertyScraper;
		String propertyName = this.propertyScraper.getOptionName();
		setText(propertyName);
	}

	public PropertyScraper getPropertyScraper() {
		return propertyScraper;
	}

}
