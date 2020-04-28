package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.application.ScraperPaths;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public abstract class CitationScraper extends CsvConvertiblePagePropertyScraper {

	public CitationScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
		createFileWriter();
	}

	private void createFileWriter() {
		CsvFileWriter citationFileWriter = new CsvFileWriter();
		setFileWriter(citationFileWriter);
		setFileWriterColumnNames(citationFileWriter);
	}

	private void setFileWriterColumnNames(CsvFileWriter csvFileWriter) {
		List<String> columnNames = Arrays.asList(
			getColumnNames()
		);

		csvFileWriter.setColumnNames(columnNames);
	}

	private static String[] getColumnNames() {
		return new String[] {"Source", "Target", "Cited by"};
	}

	@Override
	public void initialize(String rootDirectory) {
		super.initialize(rootDirectory);
		setRelativeFileWriterFile(ScraperPaths.CSV_DIRECTORY + getCsvName() + ".csv");
	}

	@Override
	protected void processProperties(Document document) throws NoSuchPropertyException {
		processCitationElements(retrieveCitationElements(), document);
	}

	private Elements retrieveCitationElements() {
		try {
			String citationSelector = getCitationSelector();
			return select(citationSelector);
		}
		catch (NoSuchPropertyException exception) {
			return new Elements(0); // It is not an error if documents have no citations
		}
	}

	private void processCitationElements(Elements citationElements, Document document) throws NoSuchPropertyException {
		for (Element citationElement : citationElements) {
			String otherDocumentIdentifier = retrieveOtherDocumentIdentifier(citationElement);
			char originCharacter = retrieveOriginCharacter(citationElement);

			pushToNextLayerDocuments(otherDocumentIdentifier);
			pushCitationToProperties(document.identifier, otherDocumentIdentifier, originCharacter);
		}
	}

	private String retrieveOtherDocumentIdentifier(Element citationElement) throws NoSuchPropertyException {
		Element identifierElement = citationElement.selectFirst("span[itemprop=publicationNumber]");

		if (identifierElement == null) {
			throw new NoSuchPropertyException();
		}

		return identifierElement.ownText();
	}

	private char retrieveOriginCharacter(Element citationElement) {
		Element originElement = citationElement.selectFirst("span[itemprop=examinerCited]");

		if (originElement == null) {
			return 0;
		}

		return originElement.ownText().charAt(0);
	}

	private void pushToNextLayerDocuments(String otherDocumentIdentifier) {
		pushNextLayerDocument(
			new Document(otherDocumentIdentifier)
		);
	}

	private void pushCitationToProperties(
		String documentIdentifier, String otherDocumentIdentifier, char originCharacter)
	{
		boolean isGivenCitation = isGivenCitation();

		String source = isGivenCitation
			? otherDocumentIdentifier : documentIdentifier;

		String target = isGivenCitation
			? documentIdentifier : otherDocumentIdentifier;

		pushProperty(
			new Citation(source, target, originCharacter)
		);
	}

	protected abstract boolean isGivenCitation();

	protected abstract String getCsvName();

	protected abstract String getCitationSelector();

}
