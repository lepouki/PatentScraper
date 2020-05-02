package scraper.core.properties;

import scraper.core.Document;

public class TypeScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Type";

	private String type;

	public TypeScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		type = selectFirst("meta[itemprop=type]").attr("content");
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {type};
	}

}
