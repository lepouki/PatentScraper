package scraper.core;

public class SimilarDocument implements CsvConvertible {

	public Document document, similarDocument;

	public SimilarDocument(Document document, String similarDocumentIdentifier) {
		this.document = document;
		this.similarDocument = new Document(similarDocumentIdentifier);
	}

	@Override
	public String[] toCsvEntries() {
		return new String[] {document.identifier, similarDocument.identifier};
	}

}
