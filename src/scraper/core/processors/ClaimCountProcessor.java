package scraper.core.processors;

import org.jsoup.nodes.Element;

public class ClaimCountProcessor extends IntegerPagePropertyProcessor {

	private static final String PROPERTY_NAME = "Claim count";

	public ClaimCountProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected int getValue() throws NoSuchPropertyException {
		String selector = HasClaimsProcessor.getClaimsSectionSelector();
		Element claimsSection = selectFirst(selector);
		return getClaimCountInClaimsSection(claimsSection);
	}

	private int getClaimCountInClaimsSection(Element section) {
		String claimCountString = section.selectFirst("span[itemprop=count]").ownText();
		return Integer.parseInt(claimCountString);
	}

}
