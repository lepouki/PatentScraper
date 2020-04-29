package scraper.core.scrapers;

public class WithAbstractScraper extends BooleanPagePropertyScraper {

	private static final String READABLE_NAME = "With abstract";

	public WithAbstractScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		selectFirst("div[class=abstract]");
		return true;
	}

}
