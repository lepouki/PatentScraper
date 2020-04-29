package scraper.core.scrapers;

public class CitationScraperNonPatent extends CitationScraper {

	private static final String READABLE_NAME = "Cited scholars";

	public CitationScraperNonPatent(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	protected boolean isGivenCitation() {
		return true;
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=detailedNonPatentLiterature]";
	}

}
