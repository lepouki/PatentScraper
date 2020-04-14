package scraper.core.processors;

import scraper.core.Document;

public class InventorOrAuthorProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "inventor or author";

	private String inventorOrAuthor;

	public InventorOrAuthorProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String selector = getSelector();
		inventorOrAuthor = selectFirst(selector).ownText();
	}

	@Override
	protected String getPropertyValue() {
		return inventorOrAuthor;
	}

	public static String getSelector() {
		return "dd[itemprop=inventor]";
	}

}
