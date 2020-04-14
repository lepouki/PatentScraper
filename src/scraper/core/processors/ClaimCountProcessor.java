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

	private int getClaimCountInClaimsSection(Element claimsSection) {
		Element claimCountElement = claimsSection.selectFirst("span[itemprop=count]");

		if (claimCountElement == null) {
			return 0;
		}

		return parseClaimCountElement(claimCountElement);
	}

	private int parseClaimCountElement(Element claimCountElement) {
		String claimCountText = claimCountElement.ownText();
		return Integer.parseInt(claimCountText);
	}

}
