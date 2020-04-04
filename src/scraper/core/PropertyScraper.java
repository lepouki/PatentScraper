package scraper.core;

public class PropertyScraper {

	private WriteTarget writeTarget;
	private PropertyRetriever propertyRetriever;

	public PropertyScraper(WriteTarget writeTarget, PropertyRetriever propertyRetriever) {
		this.writeTarget = writeTarget;
		this.propertyRetriever = propertyRetriever;
	}

	public String getPropertyName() {
		return propertyRetriever.getPropertyName();
	}

	public void scrapeProperty(Document document) {
		String property = propertyRetriever.retrievePropertyData(document);
		writeTarget.write(property);
	}

}
