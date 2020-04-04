package scraper.core;

public class PropertyRetriever {

	private String propertyName;

	public PropertyRetriever(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String retrievePropertyData(Document document) {
		return "";
	}

}
