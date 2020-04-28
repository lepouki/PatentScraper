package scraper.core.scrapers;

public class HasAbstractScraper extends BooleanPagePropertyScraper {

	private static final String READABLE_NAME = "Has abstract";

	public HasAbstractScraper(PageScraper pageScraper) {
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
