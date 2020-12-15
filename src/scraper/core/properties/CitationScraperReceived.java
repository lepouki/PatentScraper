package scraper.core.properties;

public class CitationScraperReceived extends CitationScraper {

	private static final String READABLE_NAME = "Citing patents";

	public CitationScraperReceived(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	protected boolean isGivenCitation() {
		return false;
	}

	@Override
	protected String getCitationSelector() {
		return getReceivedCitationSelector();
	}

	public static String getReceivedCitationSelector() {
		return "tr[itemprop=backwardReferencesOrig]";
	}

}
