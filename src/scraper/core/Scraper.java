package scraper.core;

import scraper.core.options.Options;

import java.util.List;

public class Scraper {

	public Scraper(Options options, List<Document> documents) {
		setOptions(options);
		setDocuments(documents);
	}

	public void setOptions(Options options) { this.options = options; }

	public void setDocuments(List<Document> documents) { this.documents = documents; }

	public void scrape() {
		for (Document document : documents) {
			scrapeDocument(document);
		}
	}

	private void scrapeDocument(Document document) {
	}

	private Options options;
	private List<Document> documents;

}
