package scraper.core.processors;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class IntermediateClassificationProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "intermediate classification";

	private String intermediateClassificationCode;

	public IntermediateClassificationProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element firstClassification = retrieveFirstClassification();
		Element intermediateClassificationElement = retrieveIntermediateClassificationElement(firstClassification);
		intermediateClassificationCode = intermediateClassificationElement.ownText();
	}

	private Element retrieveFirstClassification() throws NoSuchPropertyException {
		return selectFirst("ul[itemprop=cpcs]");
	}

	private Element retrieveIntermediateClassificationElement(Element classification) {
		final int intermediateClassificationIndex = 2;
		return classification.select("span[itemprop=Code]").get(intermediateClassificationIndex);
	}

	@Override
	protected String getPropertyValue() {
		return intermediateClassificationCode;
	}

}
