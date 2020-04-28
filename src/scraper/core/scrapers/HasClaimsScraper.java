package scraper.core.scrapers;

public class HasClaimsScraper extends BooleanPagePropertyScraper {

	private static final String READABLE_NAME = "Has claims";

	public HasClaimsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		String selector = ClaimsScraper.getClaimsSelector();
		selectFirst(selector);
		return true;
	}

}
