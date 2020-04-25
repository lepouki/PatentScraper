package scraper.core;

public class SimilarDocument extends CsvConvertible {

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
