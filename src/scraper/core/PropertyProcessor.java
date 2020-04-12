package scraper.core;

public abstract class PropertyProcessor {

	public static class NoSuchPropertyException extends Exception {}

	private Scraper scraper;
	private final String propertyName;

	public PropertyProcessor(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setScraper(Scraper scraper) {
		this.scraper = scraper;
	}

	public abstract void initializeForNextLayer();

	public abstract void processDocument(Document document) throws NoSuchPropertyException;

	public abstract String retrievePropertyData();

	protected void pushNextLayerDocument(Document document) {
		scraper.pushNextLayerDocument(document);
	}

}
