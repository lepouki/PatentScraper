package scraper.core;

import java.util.ArrayList;
import java.util.List;

public class ScraperLayerSwapper {

	private List<Document> documents;
	private List<Document> nextLayerDocuments;

	public ScraperLayerSwapper(List<Document> documents) {
		this.documents = documents;
		nextLayerDocuments = new ArrayList<>();
	}

	public int getDocumentCount() {
		return documents.size();
	}

	public Document getDocument(int documentIndex) {
		return documents.get(documentIndex);
	}

	public void swapToNextLayerDocuments() {
		documents = nextLayerDocuments;
		nextLayerDocuments = new ArrayList<>();
	}

	public void pushDocumentsToProgressionCounter(ScraperProgressionCounter progressionCounter) {
		progressionCounter.pushDocuments(documents);
	}

	public void pushNextLayerDocument(Document document) {
		nextLayerDocuments.add(document);
	}

}
