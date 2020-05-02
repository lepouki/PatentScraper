package scraper.core.properties;

import scraper.core.Document;

public class PublicationDateScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Publication date";

	private String publicationDate;

	public PublicationDateScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		publicationDate = selectFirst("time[itemprop=publicationDate]").ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {publicationDate};
	}

}
