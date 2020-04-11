package scraper.core;

import java.io.File;

public abstract class PropertyScraper {

	private final FileWriter fileWriter;
	private final PropertyProcessor propertyProcessor;
	private int successCount;

	public PropertyScraper(FileWriter fileWriter, PropertyProcessor propertyProcessor) {
		this.fileWriter = fileWriter;
		this.propertyProcessor = propertyProcessor;
		successCount = 0;
	}

	public String getPropertyName() {
		return propertyProcessor.getPropertyName();
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setRootDirectory(String rootDirectory) {
		String outputFilePath = rootDirectory + File.separator + getRelativeFileWriterPath();
		fileWriter.openFile(outputFilePath);
	}

	public void closeFileWriter() {
		fileWriter.close();
	}

	public void scrapeProperty(Document document) {
		try {
			propertyProcessor.processDocument(document);
			String property = propertyProcessor.retrievePropertyData();

			++successCount;
			fileWriter.write(property);
		}
		catch (PropertyProcessor.NoSuchPropertyException ignored) {}
	}

	public abstract String getRelativeFileWriterPath();

}
