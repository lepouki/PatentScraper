package scraper.core.scrapers;

public class WithDescriptionScraper extends BooleanPagePropertyScraper {

	private static final String READABLE_NAME = "With description";

	public WithDescriptionScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		selectFirst("section[itemprop=description]");
		return true;
	}

}
