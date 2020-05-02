package scraper.core.properties;

public class CitationCountScraperNonPatent extends ElementCountScraper {

	private static final String READABLE_NAME = "Non patent citation count";

	public CitationCountScraperNonPatent(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	protected String getElementSelector() {
		return "tr[itemprop=detailedNonPatentLiterature]";
	}

}
