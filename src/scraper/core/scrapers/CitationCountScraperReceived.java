package scraper.core.scrapers;

public class CitationCountScraperReceived extends ElementCountScraper {

	private static final String OPTION_NAME = "Received citation count";

	public CitationCountScraperReceived(PageScraper pageScraper) {
		super(OPTION_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"received citation count"};
	}

	@Override
	protected String getElementSelector() {
		return "tr[itemprop=forwardReferencesOrig]";
	}

}