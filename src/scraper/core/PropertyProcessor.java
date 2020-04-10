package scraper.core;

public abstract class PropertyProcessor {

	public static class NoSuchPropertyException extends Exception {}

	private final String propertyName;

	public PropertyProcessor(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public abstract void processDocument(Document document) throws NoSuchPropertyException;

	public abstract String retrievePropertyData();

}
