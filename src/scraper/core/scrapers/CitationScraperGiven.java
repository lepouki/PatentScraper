package scraper.core.scrapers;

public class CitationScraperGiven extends CitationScraper {

	private static final String READABLE_NAME = "Cited documents";

	public CitationScraperGiven(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	protected boolean isGivenCitation() {
		return true;
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=forwardReferencesOrig]";
	}

}
