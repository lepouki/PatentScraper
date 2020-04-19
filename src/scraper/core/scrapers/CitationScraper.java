package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;

import java.util.*;

public abstract class CitationScraper extends PagePropertyScraper {

	private final List<Citation> documentCitations;

	public CitationScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
		documentCitations = new ArrayList<>();
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileWriterFile(rootDirectory + "/csv/Citations.csv");
	}

	@Override
	public void cleanup() {
		closeFileWriter();
	}

	@Override
	public String[] getPropertyNames() {
		return getColumnNames();
	}

	public static String[] getColumnNames() {
		return new String[] {"source", "target"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		documentCitations.clear();
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
			pushToNextLayerDocuments(otherDocumentIdentifier);
			pushCitationToCitations(document.identifier, otherDocumentIdentifier);
		}
	}

	private String retrieveOtherDocumentIdentifier(Element citationElement) throws NoSuchPropertyException {
		Element identifierElement = citationElement.selectFirst("span[itemprop=publicationNumber]");

		if (identifierElement == null) {
			throw new NoSuchPropertyException();
		}

		return identifierElement.ownText();
	}

	private void pushToNextLayerDocuments(String otherDocumentIdentifier) {
		pushNextLayerDocument(
			new Document(otherDocumentIdentifier)
		);
	}

	private void pushCitationToCitations(String documentIdentifier, String otherDocumentIdentifier) {
		boolean isGivenCitation = isGivenCitation();

		String source = isGivenCitation
			? otherDocumentIdentifier : documentIdentifier;

		String target = isGivenCitation
			? documentIdentifier : otherDocumentIdentifier;

		documentCitations.add(
			new Citation(source, target)
		);
	}

	@Override
	public String[] getPropertyData() {
		int propertyCount = getPropertyNames().length;
		int citationCount = documentCitations.size();
		String[] propertyData = new String[citationCount * propertyCount];

		for (int i = 0; i < citationCount; ++i) {
			Citation citation = documentCitations.get(i);
			int index = i * propertyCount;
			pushCitationToPropertyData(citation, index, propertyData);
		}

		return propertyData;
	}

	private void pushCitationToPropertyData(Citation citation, int index, String[] propertyData) {
		propertyData[index] = citation.source;
		propertyData[index + 1] = citation.target;
	}

	protected abstract boolean isGivenCitation();

	protected abstract String getCitationSelector();

}
