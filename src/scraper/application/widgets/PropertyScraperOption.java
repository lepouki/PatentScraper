package scraper.application.widgets;

import scraper.core.OptionPropertyScraper;
import scraper.core.PropertyScraper;

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
