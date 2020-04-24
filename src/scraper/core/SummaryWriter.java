package scraper.core;

import scraper.core.writers.*;

import java.io.IOException;
import java.util.*;

public class SummaryWriter {

	private final int documentCount;
	private final List<PropertyScraper> propertyScrapers;

	public SummaryWriter(int documentCount, List<PropertyScraper> propertyScrapers) {
		this.documentCount = documentCount;
		this.propertyScrapers = propertyScrapers;
	}

	public void writeSummary(String filePath) {
		FileWriter summaryFileWriter = createFileWriter(filePath);
		writeSummaryToFileWriter(summaryFileWriter);
	}

	private FileWriter createFileWriter(String filePath) {
		return tryCreateFileWriter(filePath);
	}

	private FileWriter tryCreateFileWriter(String filePath) {
		try {
			CsvFileWriter fileWriter = new CsvFileWriter();
			setColumns(fileWriter);
			fileWriter.setFile(filePath);
			return fileWriter;
		}
		catch (IOException ignored) {
			return new DummyFileWriter();
		}
	}

	private void setColumns(CsvFileWriter fileWriter) {
		int columnCount = propertyScrapers.size() + 1;
		List<String> columnNames = new ArrayList<>(columnCount);

		columnNames.add("Total documents");
		pushPropertyScraperNamesToColumnNames(columnNames);

		fileWriter.setColumnNames(columnNames);
	}

	private void pushPropertyScraperNamesToColumnNames(List<String> columnNames) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			String propertyScraperName = propertyScraper.getReadableName();
			columnNames.add(propertyScraperName);
		}
	}

	private void writeSummaryToFileWriter(FileWriter fileWriter) {
		writeIntegerToFileWriter(documentCount, fileWriter);
		writePropertyScraperSuccessCountsToFileWriter(fileWriter);
		tryCloseFileWriter(fileWriter);
	}

	private void writeIntegerToFileWriter(int integer, FileWriter fileWriter) {
		String integerString = Integer.toString(integer);
		tryWriteEntryToFileWriter(integerString, fileWriter);
	}

	private void tryWriteEntryToFileWriter(String entry, FileWriter fileWriter) {
		try {
			fileWriter.write(entry);
		}
		catch (IOException ignored) {}
	}

	private void writePropertyScraperSuccessCountsToFileWriter(FileWriter fileWriter) {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			writeIntegerToFileWriter(propertyScraper.getSuccessCount(), fileWriter);
		}
	}

	private void tryCloseFileWriter(FileWriter fileWriter) {
		try {
			fileWriter.close();
		}
		catch (IOException ignored) {}
	}

}
