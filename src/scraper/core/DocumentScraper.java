package scraper.core;

import java.util.List;

public class DocumentScraper {

	private final List<PropertyScraper> propertyScrapers;

	public DocumentScraper(List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = propertyScrapers;
	}

	public List<PropertyScraper> getPropertyScrapers() {
		return propertyScrapers;
	}

	public void scrape(Document document) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.scrapeProperty(document);
		}
	}

	public void resetPropertyScraperSuccessCounts() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.resetSuccessCount();
		}
	}

	public void cleanupPropertyScrapers() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.cleanupResources();
		}
	}

}
