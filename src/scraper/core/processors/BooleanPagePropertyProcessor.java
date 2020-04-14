package scraper.core.processors;

import scraper.core.Document;

public abstract class BooleanPagePropertyProcessor extends SinglePropertyPageProcessor {

	private boolean value;

	public BooleanPagePropertyProcessor(String propertyName, PageProcessor pageProcessor) {
		super(propertyName, pageProcessor);
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
	public String getPropertyValue() {
		return value ? "Yes" : "No";
	}

	protected abstract boolean getValue() throws NoSuchPropertyException;

}
