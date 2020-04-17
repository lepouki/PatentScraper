package scraper.core.scrapers;

public abstract class ElementCountScraper extends IntegerPagePropertyScraper {

	public ElementCountScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
	}

	@Override
	protected int getValue() throws NoSuchPropertyException {
		String selector = getElementSelector();
		return select(selector).size();
	}

	protected abstract String getElementSelector();

}
