package scraper.core.scrapers;

import org.jsoup.nodes.Element;

public class ClaimCountScraper extends IntegerPagePropertyScraper {

	private static final String READABLE_NAME = "Claim count";

	public ClaimCountScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"claim count"};
	}

	@Override
	protected int getValue() throws NoSuchPropertyException {
		String selector = HasClaimsScraper.getClaimsSectionSelector();
		Element claimsSection = selectFirst(selector);
		return retrieveClaimCountInClaimsSection(claimsSection);
	}

	private int retrieveClaimCountInClaimsSection(Element claimsSection) {
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
