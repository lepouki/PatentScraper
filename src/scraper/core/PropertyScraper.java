package scraper.core;

import scraper.core.writers.DummyFileDataWriter;

public class PropertyScraper {

	private FileDataWriter fileDataWriter;
	private int successCount;
	private final PropertyProcessor propertyProcessor;

	public PropertyScraper(PropertyProcessor propertyProcessor) {
		successCount = 0;
		this.propertyProcessor = propertyProcessor;

		setFileDataWriter(
			new DummyFileDataWriter()
		);
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

	public void setFileDataWriter(FileDataWriter fileDataWriter) {
		this.fileDataWriter = fileDataWriter;
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

	public void initialize(String rootDirectory) {
	}

	public void cleanup() {
	}

	protected void setDataWriterFile(String filePath) {
		fileDataWriter.setFile(filePath);
	}

	protected void closeDataWriter() {
		fileDataWriter.close();
	}

}
