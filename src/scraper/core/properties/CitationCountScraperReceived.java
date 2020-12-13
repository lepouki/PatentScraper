package scraper.core.properties;

public class CitationCountScraperReceived extends ElementCountScraper {

	private static final String READABLE_NAME = "Received citation count";

	public CitationCountScraperReceived(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	protected String getElementSelector() {
		return CitationScraperReceived.getReceivedCitationSelector();
	}

}
