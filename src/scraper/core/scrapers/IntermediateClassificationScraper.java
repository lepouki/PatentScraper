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
	protected String[] getPropertyData() {
		return new String[] {intermediateClassificationCode};
	}

}
