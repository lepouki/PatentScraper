package scraper.core;

import scraper.core.writers.DummyFileDataWriter;

import java.io.IOException;
import java.util.Base64;

public class PropertyScraper {

	public static class NoSuchPropertyException extends Exception {}

	private final String readableName;
	private int successCount;
	private FileDataWriter fileDataWriter;
	private Scraper scraper;

	public PropertyScraper(String readableName) {
		successCount = 0;
		this.readableName = readableName;

		setFileDataWriter(
			new DummyFileDataWriter()
		);
	}

	public String getReadableName() {
		return readableName;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setFileDataWriter(FileDataWriter fileDataWriter) {
		this.fileDataWriter = fileDataWriter;
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
			String[] entries = getPropertyData();
			writePropertyDataToFileDataWriter(entries);
		}
		catch (NoSuchPropertyException exception) {
			writeEmptyEntriesToFileDataWriter(); // If we fail, write empty entries instead
		}
	}

	protected void cleanupForNextDocument() {
	}

	private void writePropertyDataToFileDataWriter(String[] entries) {
		++successCount;
		tryWriteToFileDataWriter(entries);
	}

	private void tryWriteToFileDataWriter(String[] entries) {
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
			fileDataWriter.write(entries);
		}
	}

	private void writeBinaryEntries(String[] entries) throws IOException {
		for (String entry : entries) {
			byte[] entryData = Base64.getDecoder().decode(entry);
			fileDataWriter.writeBytes(entryData);
		}
	}

	private void writeEmptyEntriesToFileDataWriter() {
		String[] emptyEntry = new String[] {""};
		int entryCount = getPropertyNames().length;

		for (int i = 0; i < entryCount; ++i) {
			tryWriteToFileDataWriter(emptyEntry);
		}
	}

	protected boolean isPropertyDataBinary() {
		return false;
	}

	public void initialize(String rootDirectory) {
	}

	protected void cleanup() {
	}

	protected void setFileDataWriterFile(String filePath) {
		try {
			fileDataWriter.setFile(filePath);
		}
		catch (IOException ignored) {}
	}

	protected void closeFileDataWriter() {
		try {
			fileDataWriter.close();
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
