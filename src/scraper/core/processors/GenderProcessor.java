package scraper.core.processors;

import scraper.core.Document;

public class GenderProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Gender";

	public GenderProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String inventorOrAuthor = getInventorOrAuthor();
	}

	private String getInventorOrAuthor() throws NoSuchPropertyException {
		String selector = InventorOrAuthorProcessor.getSelector();
		return selectFirst(selector).ownText();
	}

	@Override
	public String getPropertyData() {
		return "Unknown";
	}

	public float getGenderProbability() {
		return 0.0f;
	}

}
