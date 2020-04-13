package scraper.core;

public abstract class PropertyScraper {

	private final FileDataWriter fileDataWriter;
	private final PropertyProcessor propertyProcessor;
	private int successCount;

	public PropertyScraper(FileDataWriter fileDataWriter, PropertyProcessor propertyProcessor) {
		this.fileDataWriter = fileDataWriter;
		this.propertyProcessor = propertyProcessor;
		successCount = 0;
	}

	public String getPropertyName() {
		return propertyProcessor.getPropertyName();
	}

	public int getSuccessCount() {
		return successCount;
	}

	public PropertyProcessor getPropertyProcessor() {
		return propertyProcessor;
	}

	public void setScraper(Scraper scraper) {
		propertyProcessor.setScraper(scraper);
	}

	public void initializeForNextLayer() {
		propertyProcessor.initializeForNextLayer();
	}

	public void scrapeProperty(Document document) {
		String propertyData = "";

		try {
			propertyProcessor.processDocument(document);
			propertyData = propertyProcessor.getPropertyData();
			++successCount;
		}
		catch (PropertyProcessor.NoSuchPropertyException ignored) {}

		fileDataWriter.write(propertyData);
	}

	public abstract void initialize(String rootDirectory);

	public abstract void cleanup();

	protected void setDataWriterFile(String filePath) {
		fileDataWriter.setFile(filePath);
	}

	protected void closeDataWriter() {
		fileDataWriter.close();
	}

}
