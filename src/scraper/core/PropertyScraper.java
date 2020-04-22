package scraper.core;

import scraper.core.writers.DummyFileWriter;

import java.io.IOException;
import java.util.Base64;

public class PropertyScraper {

	public static class NoSuchPropertyException extends Exception {}

	private final String readableName;
	private int successCount;
	private FileWriter fileWriter;
	private Scraper scraper;

	public PropertyScraper(String readableName) {
		successCount = 0;
		this.readableName = readableName;

		setFileWriter(
			new DummyFileWriter()
		);
	}

	public String getReadableName() {
		return readableName;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	public void setScraper(Scraper scraper) {
		this.scraper = scraper;
	}

	public void scrapeProperty(Document document) {
		prepareForDocument(document);
		tryProcessDocumentProperty(document);
		cleanupForNextDocument();
	}

	protected void prepareForDocument(Document document) {
	}

	private void tryProcessDocumentProperty(Document document) {
		try {
			processDocument(document);

			writePropertyDataToFileWriter(
				getPropertyData()
			);
		}
		catch (NoSuchPropertyException exception) {
			writeEmptyEntriesToFileWriter(); // If we fail, write empty entries instead
		}
	}

	protected void cleanupForNextDocument() {
	}

	private void writePropertyDataToFileWriter(String[] entries) {
		++successCount;
		tryWriteToFileWriter(entries);
	}

	private void tryWriteToFileWriter(String[] entries) {
		try {
			writeEntries(entries);
		}
		catch (IOException ignored) {}
	}

	private void writeEntries(String[] entries) throws IOException {
		boolean isBinary = isPropertyDataBinary();

		if (isBinary) {
			writeBinaryEntries(entries);
		}
		else {
			fileWriter.write(entries);
		}
	}

	private void writeBinaryEntries(String[] entries) {
		for (String entry : entries) {
			writeBinaryToFileWriter(entry);
		}
	}

	protected void writeBinaryToFileWriter(String binary) {
		try {
			byte[] data = Base64.getDecoder().decode(binary);
			fileWriter.writeBytes(data);
		}
		catch (IOException ignored) {}
	}

	private void writeEmptyEntriesToFileWriter() {
		String[] emptyEntry = new String[] {""};
		int entryCount = getPropertyNames().length;

		for (int i = 0; i < entryCount; ++i) {
			tryWriteToFileWriter(emptyEntry);
		}
	}

	protected boolean isPropertyDataBinary() {
		return false;
	}

	public void initialize(String rootDirectory) {
	}

	protected void cleanup() {
	}

	protected void setFileWriterFile(String filePath) {
		try {
			fileWriter.setFile(filePath);
		}
		catch (IOException ignored) {}
	}

	protected void closeFileWriter() {
		try {
			fileWriter.close();
		}
		catch (IOException ignored) {}
	}

	protected void pushNextLayerDocument(Document document) {
		scraper.pushNextLayerDocument(document);
	}

	public String[] getPropertyNames() {
		return new String[0];
	}

	protected void processDocument(Document document) throws NoSuchPropertyException {
	}

	protected String[] getPropertyData() {
		return new String[0];
	}

}
