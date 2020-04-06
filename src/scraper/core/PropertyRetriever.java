package scraper.core;

public abstract class PropertyRetriever {

	private String propertyName;

	public PropertyRetriever(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public abstract String retrievePropertyData(Document document);

}
