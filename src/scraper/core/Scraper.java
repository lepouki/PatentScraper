package scraper.core;

import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.events.EventSource;

import java.util.ArrayList;
import java.util.List;

public class Scraper extends EventSource {

	public static class DocumentProcessed extends Event {

		private Progress progress;

		public DocumentProcessed(Object source, Progress progress) {
			super(source);
			this.progress = progress;
		}

		public Progress getProgress() {
			return progress;
		}

	}

	public static class PropertyProcessed extends Event {

		private Progress progress;

		public PropertyProcessed(Object source, Progress progress) {
			super(source);
			this.progress = progress;
		}

		public Progress getProgress() {
			return progress;
		}

	}

	public static class Progress {

		private float percentage;
		private String lastElementProcessed;

		public Progress(float percentage, String lastElementProcessed) {
			this.percentage = percentage;
			this.lastElementProcessed = lastElementProcessed;
		}

		public float getPercentage() {
			return percentage;
		}

		public String getLastElementProcessed() {
			return lastElementProcessed;
		}

	}

	public static Scraper createEmptyScraper() {
		List<Document> documents = new ArrayList<>(0);
		List<PropertyScraper> propertyScrapers = new ArrayList<>(0);
		return new Scraper(documents, propertyScrapers);
	}

	private List<Document> documents;
	private List<PropertyScraper> propertyScrapers;

	public Scraper(List<Document> documents, List<PropertyScraper> propertyScrapers) {
		setDocuments(documents);
		setPropertyScrapers(propertyScrapers);
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setPropertyScrapers(List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = propertyScrapers;
	}

	public void scrapeDocuments() {
		for (int i = 0; i < documents.size(); ++i) {
			Document document = documents.get(i);
			scrapeProperties(document);
			notifyEventListenersDocumentProcessed(i, document);
		}
	}

	private void notifyEventListenersDocumentProcessed(int documentIndex, Document document) {
		Progress progress = new Progress(calculateDocumentProgressPercentage(documentIndex), document.identifier);

		notifyEventListeners(
			new DocumentProcessed(this, progress)
		);
	}

	private float calculateDocumentProgressPercentage(int documentIndex) {
		return (float)(documentIndex + 1) / documents.size() * 100.0f;
	}

	private void scrapeProperties(Document document) {
		for (int i = 0; i < propertyScrapers.size(); ++i) {
			PropertyScraper propertyScraper = propertyScrapers.get(i);
			propertyScraper.scrapeProperty(document);
			notifyEventListenersPropertyProcessed(i, propertyScraper);
		}
	}

	private void notifyEventListenersPropertyProcessed(int propertyIndex, PropertyScraper propertyScraper) {
		String property = propertyScraper.getProperty();
		Progress progress = new Progress(calculatePropertyProgressPercentage(propertyIndex), property);

		notifyEventListeners(
			new PropertyProcessed(this, progress)
		);
	}

	private float calculatePropertyProgressPercentage(int propertyIndex) {
		return (float)(propertyIndex + 1) / propertyScrapers.size() * 100.0f;
	}

}
