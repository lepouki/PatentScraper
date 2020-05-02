package scraper.core.properties;

import scraper.core.Document;

public abstract class IntegerPagePropertyScraper extends PagePropertyScraper {

	private int value;

	public IntegerPagePropertyScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
	}

	@Override
	public void processDocument(Document document) {
		try {
			value = getValue();
		}
		catch (NoSuchPropertyException exception) {
			value = 0;
		}
	}

	@Override
	public String[] getPropertyData() {
		String valueString = Integer.toString(value);
		return new String[] {valueString};
	}

	protected abstract int getValue() throws NoSuchPropertyException;

}
