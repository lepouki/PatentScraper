package scraper.core;

public class PropertyRetriever {

	private String property;

	public PropertyRetriever(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	/**
	 * Gets overridden by the actual PropertyRetriever classes.
	 */
	public String retrievePropertyData(Document document) {
		return "";
	}

}
