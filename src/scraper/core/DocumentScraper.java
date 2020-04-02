package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventSource;

import java.util.List;

public class DocumentScraper extends EventSource {

	public static class Progress extends scraper.core.Progress {

		private String lastProperty;

		public Progress(float percentage, String lastProperty) {
			super(percentage);
			this.lastProperty = lastProperty;
		}

		@Override
		public String getLastItemProcessed() {
			return lastProperty;
		}

	}

	private List<PropertyWriter> propertyWriters;

	public DocumentScraper(List<PropertyWriter> propertyWriters) {
		setPropertyWriters(propertyWriters);
	}

	public void setPropertyWriters(List<PropertyWriter> propertyWriters) {
		this.propertyWriters = propertyWriters;
	}

	public void scrapeDocumentProperties(Document document) {
		for (int i = 0; i < propertyWriters.size(); ++i) {
			PropertyWriter propertyWriter = propertyWriters.get(i);
			propertyWriter.writePropertyData(document);
			notifyEventListenersPropertyScraped(i, propertyWriter);
		}
	}

	private void notifyEventListenersPropertyScraped(int propertyIndex, PropertyWriter propertyWriter) {
		float progressPercentage = calculatePropertyProgressPercentage(propertyIndex);

		String property = propertyWriter.getProperty();
		Progress progress = new Progress(progressPercentage, property);

		notifyEventListenersProgress(progress);
	}

	private float calculatePropertyProgressPercentage(int propertyIndex) {
		return (float)(propertyIndex + 1) / propertyWriters.size() * 100.0f;
	}

	private void notifyEventListenersProgress(Progress progress) {
		ProgressEvent event = new ProgressEvent(this, progress);
		notifyEventListeners(event);
	}

}
