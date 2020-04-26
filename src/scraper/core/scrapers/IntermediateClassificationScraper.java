package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class IntermediateClassificationScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Intermediate classification";

	private String intermediateClassificationCode;

	public IntermediateClassificationScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"intermediate classification"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element firstClassification = selectFirst("ul[itemprop=cpcs]");
		Element intermediateClassificationElement = retrieveIntermediateClassificationElement(firstClassification);
		intermediateClassificationCode = intermediateClassificationElement.ownText();
	}

	private Element retrieveIntermediateClassificationElement(Element classification) {
		String selector = ClassificationsScraper.getClassificationCodeSelector();
		final int intermediateClassificationIndex = 2;
		return classification.select(selector).get(intermediateClassificationIndex);
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {intermediateClassificationCode};
	}

}
