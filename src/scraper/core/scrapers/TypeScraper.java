package scraper.core.scrapers;

import scraper.core.Document;

public class TypeScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Document type";

	private String type;

	public TypeScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"type"};
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
