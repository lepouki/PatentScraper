package scraper.core;

public class PropertyProcessor {

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

	public void initializeForNextLayer() {
	}

	public void processDocument(Document document) throws NoSuchPropertyException {
	}

	public String getPropertyData() {
		return "";
	}

	protected void pushNextLayerDocument(Document document) {
		scraper.pushNextLayerDocument(document);
	}

}
