package scraper.core.properties;

import scraper.core.Document;

public class FilingOrCreationDateScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Filing or creation date";

	private String filingOrCreationDate;

	public FilingOrCreationDateScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		filingOrCreationDate = selectFirst("time[itemprop=filingDate]").ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {filingOrCreationDate};
	}

}
