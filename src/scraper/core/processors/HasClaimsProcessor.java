package scraper.core.processors;

public class HasClaimsProcessor extends BooleanPagePropertyProcessor {

	private static final String PROPERTY_NAME = "Has claims";

	public HasClaimsProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		String selector = getClaimsSectionSelector();
		selectFirst(selector);
		return true;
	}

	public static String getClaimsSectionSelector() {
		return "section[itemprop=claims]";
	}

}
