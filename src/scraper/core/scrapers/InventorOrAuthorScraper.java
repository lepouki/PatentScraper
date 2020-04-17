package scraper.core.scrapers;

import scraper.core.Document;

public class InventorOrAuthorScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Inventor or author";

	private String inventorOrAuthor;

	public InventorOrAuthorScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"inventor or author"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String selector = getInventorSelector();
		inventorOrAuthor = selectFirst(selector).ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {inventorOrAuthor};
	}

	public static String getInventorSelector() {
		return "dd[itemprop=inventor]";
	}

}
