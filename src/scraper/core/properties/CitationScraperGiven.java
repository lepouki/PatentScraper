package scraper.core.properties;

public class CitationScraperGiven extends CitationScraper {

	private static final String READABLE_NAME = "Cited patents";

	public CitationScraperGiven(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	protected boolean isGivenCitation() {
		return true;
	}

	@Override
	protected String getCitationSelector() {
		return getGivenCitationSelector();
	}

	public static String getGivenCitationSelector() {
		return "tr[itemprop=forwardReferencesOrig]";
	}

}
