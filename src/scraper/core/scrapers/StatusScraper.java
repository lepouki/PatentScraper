package scraper.core.scrapers;

import scraper.core.Document;

public class StatusScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Status";

	private String status;

	public StatusScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) {
		try {
			status = selectFirst("span[itemprop=status]").ownText();
		}
		catch (NoSuchPropertyException exception) {
			status = "Application"; // Patents not yet granted don't have a status element
		}
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {status};
	}

}
