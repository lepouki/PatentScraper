package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public class SimilarDocumentsScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Similar documents";

	private final List<SimilarDocument> similarDocuments;

	public SimilarDocumentsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
		similarDocuments = new ArrayList<>();
	}

	@Override
	public void initialize(String rootDirectory) {
		CsvFileWriter fileWriter = new CsvFileWriter();

		setFileWriterColumns(fileWriter);
		setFileWriter(fileWriter);
		setFileWriterFile(rootDirectory + "/csv/" + READABLE_NAME + ".csv");
	}

	private void setFileWriterColumns(CsvFileWriter fileWriter) {
		List<String> columnNames = Arrays.asList(
			getColumnNames()
		);

		fileWriter.setColumnNames(columnNames);
	}

	private static String[] getColumnNames() {
		return new String[] {"document", "similar"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		similarDocuments.clear();
		Elements similarDocuments = select("tr[itemprop=similarDocuments]");

		for (Element similarDocument : similarDocuments) {
			String similarDocumentIdentifier = retrieveSimilarDocumentIdentifier(similarDocument);
			pushSimilarDocument(document, similarDocumentIdentifier);
		}
	}

	private String retrieveSimilarDocumentIdentifier(Element similarDocument) throws NoSuchPropertyException {
		Element identifier = similarDocument.selectFirst("span[itemprop=publicationNumber]");

		if (identifier == null) {
			throw new NoSuchPropertyException();
		}

		return identifier.ownText();
	}

	private void pushSimilarDocument(Document document, String similarDocumentIdentifier) {
		SimilarDocument similarDocument = new SimilarDocument(document, similarDocumentIdentifier);
		similarDocuments.add(similarDocument);
		pushNextLayerDocument(similarDocument.similarDocument);
	}

	@Override
	public String[] getPropertyData() {
		int documentCount = similarDocuments.size();
		int columnCount = getColumnNames().length;
		String[] propertyData = new String[documentCount * columnCount];

		for (int i = 0; i < documentCount; ++i) {
			SimilarDocument similarDocument = similarDocuments.get(i);
			int index = i * columnCount;
			pushSimilarDocumentToPropertyData(similarDocument, index, propertyData);
		}

		return propertyData;
	}

	private void pushSimilarDocumentToPropertyData(SimilarDocument similarDocument, int index, String[] propertyData) {
		propertyData[index] = similarDocument.document.identifier;
		propertyData[index + 1] = similarDocument.similarDocument.identifier;
	}

	@Override
	public void cleanup() {
		closeFileWriter();
	}

}
