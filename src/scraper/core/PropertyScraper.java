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

	public void setScraper(Scraper scraper) {
		propertyProcessor.setScraper(scraper);
	}

	public void initializeForNextLayer() {
		propertyProcessor.initializeForNextLayer();
	}

	public void scrapeProperty(Document document) {
		try {
			propertyProcessor.processDocument(document);
			String property = propertyProcessor.retrievePropertyData();

			++successCount;
			fileDataWriter.write(property);
		}
		catch (PropertyProcessor.NoSuchPropertyException ignored) {}
	}

	public abstract void initialize(String rootDirectory);

	public abstract void cleanup();

	public abstract String getRelativeFileWriterPath();

	protected void setFileDataWriterFile(String filePath) {
		fileDataWriter.setFile(filePath);
	}

	protected void closeFileDataWriter() {
		fileDataWriter.close();
	}

}
