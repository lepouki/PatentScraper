package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventSource;

import java.util.ArrayList;
import java.util.List;

public class Scraper extends EventSource {

	public static class ProcessingPropertyEvent extends ProgressEvent {

		public ProcessingPropertyEvent(Object source, Progress progress) {
			super(source, progress);
		}

	}

	public static class WorkDoneEvent extends ProgressEvent {

		public WorkDoneEvent(Object source) {
			super(
				source,
				new Progress(100.0f, "Done")
			);
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
			notifyEventListenersProcessingProperty(i, propertyScraper);
			propertyScraper.scrapeProperty(document);
		}

		notifyEventListenersWorkDone();
	}

	private void notifyEventListenersProcessingProperty(int propertyIndex, PropertyScraper propertyScraper) {
		String propertyName = propertyScraper.getPropertyName();
		Progress progress = new Progress(calculatePropertyProgressPercentage(propertyIndex), propertyName);

		notifyEventListeners(
			new ProcessingPropertyEvent(this, progress)
		);
	}

	private float calculatePropertyProgressPercentage(int propertyIndex) {
		return (float)(propertyIndex) / propertyScrapers.size() * 100.0f;
	}

	private void notifyEventListenersWorkDone() {
		notifyEventListeners(
			new WorkDoneEvent(this)
		);
	}

}
