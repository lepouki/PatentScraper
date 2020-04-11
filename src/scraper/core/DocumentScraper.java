package scraper.core;

import java.util.List;

public class DocumentScraper {

	private final List<PropertyScraper> propertyScrapers;

	public DocumentScraper(List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = propertyScrapers;
	}

	public void scrape(Document document) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.scrapeProperty(document);
		}
	}

}
