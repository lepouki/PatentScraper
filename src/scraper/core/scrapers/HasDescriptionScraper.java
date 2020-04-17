package scraper.core.scrapers;

public class HasDescriptionScraper extends BooleanPagePropertyScraper {

	private static final String READABLE_NAME = "Has description";

	public HasDescriptionScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"has description"};
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		selectFirst("section[itemprop=description]");
		return true;
	}

}
