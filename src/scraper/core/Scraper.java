package scraper.core;

import java.util.List;

public class Scraper {

	private final List<PropertyScraper> propertyScrapers;

	public Scraper(List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = propertyScrapers;
	}

	public void scrapeDocument(Document document) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.scrapeProperty(document);
		}
	}

}
