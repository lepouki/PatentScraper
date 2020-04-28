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
		title = selectFirst("span[itemprop=title]").ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {title};
	}

}
