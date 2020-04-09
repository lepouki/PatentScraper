package scraper.core;

public abstract class PropertyRetriever {

	public static class NoSuchPropertyException extends Exception {}

	private String propertyName;

	public PropertyRetriever(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public abstract String retrievePropertyData(Document document) throws NoSuchPropertyException;

}
