package scraper.core.properties;

import scraper.core.Document;

public class PatentOfficeScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Patent office";

	private String patentOffice;

	public PatentOfficeScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		patentOffice = selectFirst("dd[itemprop=countryName]").ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {patentOffice};
	}

}
