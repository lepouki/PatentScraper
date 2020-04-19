package scraper.core.writers;

import scraper.core.CsvCharacters;

import java.io.IOException;
import java.util.*;

public class CsvFileWriter extends BasicFileWriter {

	private int valueIndex;
	private List<String> columnNames;

	public CsvFileWriter() {
		valueIndex = 0;
		columnNames = new ArrayList<>();
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
		wrapValueIndex();
	}

	private void wrapValueIndex() {
		valueIndex = valueIndex	% columnNames.size();
	}

	@Override
	public void write(String entry) throws IOException {
		String formatted = applyFormat(entry);
		super.write(formatted);
	}

	private String applyFormat(String data) {
		String dataWithQuotesReplaced = replaceQuotes(data);
		String dataWithQuotesAdded = withQuotesWhenNeeded(dataWithQuotesReplaced);
		updateValueIndex();
		return withSeparatorOrNewLine(dataWithQuotesAdded);
	}

	private String replaceQuotes(String data) {
		return data.replace(CsvCharacters.QUOTE, CsvCharacters.QUOTE_REPLACEMENT);
	}

	private String withQuotesWhenNeeded(String data) {
		boolean quotesNeeded = containsSeparator(data);

		if (quotesNeeded) {
			return CsvCharacters.QUOTE + data + CsvCharacters.QUOTE;
		}

		return data;
	}

	private boolean containsSeparator(String data) {
		String separator = Character.toString(CsvCharacters.SEPARATOR);
		return data.contains(separator);
	}

	private void updateValueIndex() {
		++valueIndex;
		wrapValueIndex();
	}

	private String withSeparatorOrNewLine(String data) {
		return isLastValueInLine()
			? data + '\n'
			: data + CsvCharacters.SEPARATOR;
	}

	private boolean isLastValueInLine() {
		return valueIndex == 0;
	}

	@Override
	public void setFile(String filePath) throws IOException {
		super.setFile(filePath);
		valueIndex = 0;
		writeColumnNames();
	}

	private void writeColumnNames() throws IOException {
		for (String columnName : columnNames) {
			write(columnName);
		}
	}

}
