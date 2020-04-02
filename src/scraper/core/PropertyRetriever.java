package scraper.core;

public class PropertyRetriever {

	private String property;

	public PropertyRetriever(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public String retrievePropertyData(Document document) {
		return "";
	}

}
