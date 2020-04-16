package scraper.core.processors;

import org.jsoup.nodes.Element;

public class ClaimCountProcessor extends IntegerPagePropertyProcessor {

	private static final String PROPERTY_NAME = "claim count";

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

		if (claimCountElement != null) {
			return parseClaimCountElement(claimCountElement);
		}

		return countClaimElements(claimsSection);
	}

	private int parseClaimCountElement(Element claimCountElement) {
		String claimCountText = claimCountElement.ownText();
		return Integer.parseInt(claimCountText);
	}

	private int countClaimElements(Element claimsSection) {
		return claimsSection.select("claim").size();
	}

}
