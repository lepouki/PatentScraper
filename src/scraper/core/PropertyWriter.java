package scraper.core;

public class PropertyWriter {

	private WriteTarget writeTarget;
	private PropertyRetriever propertyRetriever;

	public PropertyWriter(WriteTarget writeTarget, PropertyRetriever propertyRetriever) {
		this.writeTarget = writeTarget;
		this.propertyRetriever = propertyRetriever;
	}

	public String getProperty() {
		return propertyRetriever.getProperty();
	}

	public void writePropertyData(Document document) {
		String documentPropertyData = propertyRetriever.retrievePropertyData(document);
		writeTarget.write(documentPropertyData);
	}

}
