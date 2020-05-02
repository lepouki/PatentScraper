package scraper.core.properties;

import scraper.core.Document;

public abstract class BooleanPagePropertyScraper extends PagePropertyScraper {

	private boolean value;

	public BooleanPagePropertyScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
	}

	@Override
	public void processDocument(Document document) {
		try {
			value = getValue();
		}
		catch (NoSuchPropertyException exception) {
			value = false;
		}
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {value ? "Yes" : "No"};
	}

	protected abstract boolean getValue() throws NoSuchPropertyException;

}
