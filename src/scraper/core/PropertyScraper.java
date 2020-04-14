package scraper.core;

import scraper.core.processors.GenderWithProbabilityProcessor;
import scraper.core.writers.DummyFileDataWriter;

import java.io.IOException;

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

	public String[] getPropertyNames() {
		return propertyProcessor.getPropertyNames();
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
		try {
			propertyProcessor.processDocument(document);
			String[] propertyData = propertyProcessor.getPropertyData();
			writePropertyDataToFileDataWriter(propertyData);
		}
		catch (PropertyProcessor.NoSuchPropertyException exception) {
			tryWriteEmptyEntriesToFileDataWriter(); // If we fail, write empty entries instead
		}
	}

	private void writePropertyDataToFileDataWriter(String[] propertyData) {
		++successCount;
		tryWriteToFileDataWriter(propertyData);
	}

	private void tryWriteToFileDataWriter(String[] propertyData) {
		try {
			fileDataWriter.write(propertyData);
		}
		catch (IOException ignored) {}
	}

	private void tryWriteEmptyEntriesToFileDataWriter() {
		String[] emptyEntry = new String[] {""};
		int entryCount = propertyProcessor.getPropertyNames().length;

		for (int i = 0; i < entryCount; ++i) {
			tryWriteToFileDataWriter(emptyEntry);
		}
	}

	public void initialize(String rootDirectory) throws IOException {
	}

	public void cleanup() throws IOException {
	}

	protected void setDataWriterFile(String filePath) throws IOException {
		fileDataWriter.setFile(filePath);
	}

	protected void closeDataWriter() throws IOException {
		fileDataWriter.close();
	}

}
