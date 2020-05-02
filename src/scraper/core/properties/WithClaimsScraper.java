package scraper.core.properties;

public class WithClaimsScraper extends BooleanPagePropertyScraper {

	private static final String READABLE_NAME = "With claims";

	public WithClaimsScraper(PageScraper pageScraper) {
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
