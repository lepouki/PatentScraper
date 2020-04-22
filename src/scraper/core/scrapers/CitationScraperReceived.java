package scraper.core.scrapers;

public class CitationScraperReceived extends CitationScraper {

	private static final String READABLE_NAME = "Citing documents";

	public CitationScraperReceived(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	protected boolean isGivenCitation() {
		return false;
	}

	@Override
	protected String getCsvName() {
		return READABLE_NAME;
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=backwardReferencesOrig]";
	}

}
