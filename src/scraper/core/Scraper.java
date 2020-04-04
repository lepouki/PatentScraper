package scraper.core;

import scraper.core.events.EventSource;

import java.util.ArrayList;
import java.util.List;

public class Scraper extends EventSource {

	public static class PropertyProcessed extends ProgressEvent {

		public PropertyProcessed(Object source, Progress progress) {
			super(source, progress);
		}

	}

	public static Scraper createEmptyScraper() {
		List<PropertyScraper> propertyScrapers = new ArrayList<>(0);
		return new Scraper(propertyScrapers);
	}

	private List<PropertyScraper> propertyScrapers;

	public Scraper(List<PropertyScraper> propertyScrapers) {
		setPropertyScrapers(propertyScrapers);
	}

	public void setPropertyScrapers(List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = propertyScrapers;
	}

	public void scrapeDocument(Document document) {
		for (int i = 0; i < propertyScrapers.size(); ++i) {
			PropertyScraper propertyScraper = propertyScrapers.get(i);
			propertyScraper.scrapeProperty(document);
			notifyEventListenersPropertyProcessed(i, propertyScraper);
		}
	}

	private void notifyEventListenersPropertyProcessed(int propertyIndex, PropertyScraper propertyScraper) {
		String propertyName = propertyScraper.getPropertyName();
		Progress progress = new Progress(calculatePropertyProgressPercentage(propertyIndex), propertyName);

		notifyEventListeners(
			new PropertyProcessed(this, progress)
		);
	}

	private float calculatePropertyProgressPercentage(int propertyIndex) {
		return (float)(propertyIndex + 1) / propertyScrapers.size() * 100.0f;
	}

}
