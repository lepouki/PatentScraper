package scraper.core.processors;

import scraper.core.*;

public class IdentifierProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "identifier";

	private String identifier;

	public IdentifierProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) {
		identifier = retrieveIdentifier();
	}

	private String retrieveIdentifier() {
		String selector = isPatent()
			? "dd[itemprop=publicationNumber]"
			: "dd[itemprop=docID]";

		return retrieveElementTextAssured(selector);
	}

	private boolean isPatent() {
		try {
			selectFirst("dd[itemprop=publicationNumber]");
			return true;
		}
		catch (NoSuchPropertyException exception) {
			return false;
		}
	}

	private String retrieveElementTextAssured(String selector) {
		try {
			return selectFirst(selector).ownText();
		}
		catch (NoSuchPropertyException ignored) {}

		return "";
	}

	@Override
	protected String getPropertyValue() {
		return identifier;
	}

}
