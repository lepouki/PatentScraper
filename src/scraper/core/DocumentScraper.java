package scraper.core;

import java.io.IOException;
import java.util.*;

public class DocumentScraper {

	private final List<PropertyScraper> propertyScrapers;

	public DocumentScraper(List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = new ArrayList<>(propertyScrapers);
	}

	public void scrape(Document document) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.scrapeProperty(document);
		}
	}

	public void cleanupPropertyScrapers() throws IOException {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.cleanup();
		}
	}

}
