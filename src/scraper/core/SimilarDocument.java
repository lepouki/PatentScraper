package scraper.core;

public class SimilarDocument {

	public Document document, similarDocument;

	public SimilarDocument(Document document, String similarDocumentIdentifier) {
		this.document = document;
		this.similarDocument = new Document(similarDocumentIdentifier);
	}

}
