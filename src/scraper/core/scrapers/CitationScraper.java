package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public abstract class CitationScraper extends PagePropertyScraper {

	private final List<Citation> citations;

	public CitationScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
		citations = new ArrayList<>();

		CsvFileWriter citationFileWriter = new CsvFileWriter();
		setFileWriter(citationFileWriter);
		setCitationFileWriterColumnNames(citationFileWriter);
	}

	private void setCitationFileWriterColumnNames(CsvFileWriter csvFileWriter) {
		List<String> columnNames = Arrays.asList(
			getColumnNames()
		);

		csvFileWriter.setColumnNames(columnNames);
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileWriterFile(rootDirectory + "/csv/" + getCsvName() + ".csv");
	}

	@Override
	public void cleanup() {
		closeFileWriter();
	}

	private static String[] getColumnNames() {
		return new String[] {"source", "target", "cited by"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		citations.clear();
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
			pushCitationToCitations(document.identifier, otherDocumentIdentifier, originCharacter);
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

	private void pushCitationToCitations(
		String documentIdentifier, String otherDocumentIdentifier, char originCharacter)
	{
		boolean isGivenCitation = isGivenCitation();

		String source = isGivenCitation
			? otherDocumentIdentifier : documentIdentifier;

		String target = isGivenCitation
			? documentIdentifier : otherDocumentIdentifier;

		citations.add(
			new Citation(source, target, originCharacter)
		);
	}

	@Override
	public String[] getPropertyData() {
		int columnCount = getColumnNames().length;
		int citationCount = citations.size();
		String[] propertyData = new String[citationCount * columnCount];

		for (int i = 0; i < citationCount; ++i) {
			Citation citation = citations.get(i);
			int index = i * columnCount;
			pushCitationToPropertyData(citation, index, propertyData);
		}

		return propertyData;
	}

	private void pushCitationToPropertyData(Citation citation, int index, String[] propertyData) {
		propertyData[index] = citation.source;
		propertyData[index + 1] = citation.target;
		propertyData[index + 2] = citation.origin;
	}

	protected abstract boolean isGivenCitation();

	protected abstract String getCsvName();

	protected abstract String getCitationSelector();

}
