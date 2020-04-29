package scraper.core.scrapers;

import scraper.core.Document;

public class TitleScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Title";

	private String title;

	public TitleScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String selector = getTitleSelector();
		title = selectFirst(selector).ownText();
	}

	public static String getTitleSelector() {
		return "span[itemprop=title]";
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {title};
	}

}
