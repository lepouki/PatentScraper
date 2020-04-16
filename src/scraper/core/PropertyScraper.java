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

	public void scrapeProperty(Document document) {
		try {
			propertyProcessor.processDocument(document);
			String[] entries = propertyProcessor.getPropertyData();
			writePropertyDataToFileDataWriter(entries);
		}
		catch (PropertyProcessor.NoSuchPropertyException exception) {
			writeEmptyEntriesToFileDataWriter(); // If we fail, write empty entries instead
		}
	}

	private void writePropertyDataToFileDataWriter(String[] entries) {
		++successCount;
		tryWriteToFileDataWriter(entries);
	}

	private void tryWriteToFileDataWriter(String[] entries) {
		try {
			fileDataWriter.write(entries);
		}
		catch (IOException ignored) {}
	}

	private void writeEmptyEntriesToFileDataWriter() {
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

	protected void setFileDataWriterFile(String filePath) throws IOException {
		fileDataWriter.setFile(filePath);
	}

	protected void closeFileDataWriter() throws IOException {
		fileDataWriter.close();
	}

}
