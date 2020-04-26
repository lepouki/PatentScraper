package scraper.core.scrapers;

public class PageLinkScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Page link";

	public PageLinkScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"page link"};
	}

	@Override
	protected String[] getPropertyData() {
		String pageLink = getPageLink();
		return new String[] {pageLink};
	}

}
