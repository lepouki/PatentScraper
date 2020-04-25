package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public class SimilarDocumentsScraper extends CsvConvertiblePagePropertyScraper {

	private static final String READABLE_NAME = "Similar documents";

	public SimilarDocumentsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
		createFileWriter();
	}

	private void createFileWriter() {
		CsvFileWriter fileWriter = new CsvFileWriter();
		setFileWriter(fileWriter);
		setFileWriterColumnNames(fileWriter);
	}

	private void setFileWriterColumnNames(CsvFileWriter fileWriter) {
		List<String> columnNames = Arrays.asList(
			getColumnNames()
		);

		fileWriter.setColumnNames(columnNames);
	}

	private static String[] getColumnNames() {
		return new String[] {"document", "similar"};
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileWriterFile(rootDirectory + "/csv/" + READABLE_NAME + ".csv");
	}

	@Override
	public void cleanupResources() {
		closeFileWriter();
	}

	@Override
	protected void processProperties(Document document) throws NoSuchPropertyException {
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
		pushProperty(similarDocument);
		pushNextLayerDocument(similarDocument.similarDocument);
	}

}
