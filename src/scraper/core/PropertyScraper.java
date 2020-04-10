package scraper.core;

public class PropertyScraper {

	private final DataWriter dataWriter;
	private final PropertyProcessor propertyProcessor;
	private int successCount;

	public PropertyScraper(DataWriter dataWriter, PropertyProcessor propertyProcessor) {
		this.dataWriter = dataWriter;
		this.propertyProcessor = propertyProcessor;
		successCount = 0;
	}

	public String getPropertyName() {
		return propertyProcessor.getPropertyName();
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void scrapeProperty(Document document) {
		try {
			propertyProcessor.processDocument(document);
			String property = propertyProcessor.retrievePropertyData();

			++successCount;

			dataWriter.write(property);
		}
		catch (PropertyProcessor.NoSuchPropertyException ignored) {}
	}

}
