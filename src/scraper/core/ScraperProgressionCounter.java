package scraper.core;

import java.util.*;

public class ScraperProgressionCounter {

	private int documentCount;
	private final Set<Document> alreadyProcessed;

	public ScraperProgressionCounter() {
		documentCount = 0;
		alreadyProcessed = new HashSet<>();
	}

	public int getDocumentCount() {
		return documentCount;
	}

	public void incrementDocumentCount() {
		++documentCount;
	}

	public boolean isDocumentAlreadyProcessed(Document document) {
		return alreadyProcessed.contains(document);
	}

	public void pushDocuments(List<Document> documents) {
		alreadyProcessed.addAll(documents);
	}

}
