package scraper.core.processors;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.Citation;
import scraper.core.Document;

import java.util.ArrayList;
import java.util.List;

public abstract class CitationProcessor extends PagePropertyProcessor {

	private final List<Citation> citations;

	public CitationProcessor(PageProcessor pageProcessor) {
		super(pageProcessor);
		citations = new ArrayList<>();
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"source", "target"};
	}

	@Override
	public void processDocument(Document document) {
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

	private void processCitationElements(Elements citationElements, Document document) {
		for (Element citationElement : citationElements) {
			String otherDocumentIdentifier = citationElement.selectFirst("span[itemprop=publicationNumber]").ownText();
			pushToNextLayerDocuments(otherDocumentIdentifier);
			pushCitationToCitations(document.identifier, otherDocumentIdentifier);
		}
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

		citations.add(
			new Citation(source, target)
		);
	}

	@Override
	public String[] getPropertyData() {
		int propertyCount = getPropertyNames().length;
		int citationCount = citations.size();
		String[] propertyData = new String[citationCount * propertyCount];

		for (int i = 0; i < citationCount; ++i) {
			Citation citation = citations.get(i);

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
