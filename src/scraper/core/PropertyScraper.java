package scraper.core;

public class PropertyScraper {

	private DataWriter dataWriter;
	private PropertyRetriever propertyRetriever;
	private int successCount;

	public PropertyScraper(DataWriter dataWriter, PropertyRetriever propertyRetriever) {
		this.dataWriter = dataWriter;
		this.propertyRetriever = propertyRetriever;
		successCount = 0;
	}

	public String getPropertyName() {
		return propertyRetriever.getPropertyName();
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void scrapeProperty(Document document) {
		try {
			String property = propertyRetriever.retrievePropertyData(document);
			++successCount;
			dataWriter.write(property);
		}
		catch (PropertyRetriever.NoSuchPropertyException ignored) {}
	}

}
