package scraper.application.parsers;

import scraper.core.CsvCharacters;

public class GooglePatentsCsvParser extends DocumentIdentifierCsvParser {

	public static class LineSkipException extends FormatException {

		public LineSkipException(String actual, String expected) {
			super("When skipping line, got \"" + actual + "\" instead of \"" + expected + "\"");
		}

	}

	private static final String SEARCH_LINK_LINE_EXPECTED_FIRST_COLUMN = "search URL:";
	private static final String COLUMN_IDENTIFIERS_LINE_EXPECTED_FIRST_COLUMN = "id";

	@Override
	protected void prepare() throws LineSkipException {
		skipSearchLinkLine();
		skipColumnIdentifiersLine();
	}

	private void skipSearchLinkLine() throws LineSkipException {
		String searchLinkLine = getNextDocumentLine();
		checkFirstColumnInLine(searchLinkLine, SEARCH_LINK_LINE_EXPECTED_FIRST_COLUMN);
	}

	private void checkFirstColumnInLine(String line, String expected) throws LineSkipException {
		String separator = Character.toString(CsvCharacters.SEPARATOR);
		boolean correctFirstColumn = line.split(separator)[0].equals(expected);

		if (!correctFirstColumn) {
			throw new LineSkipException(line, expected);
		}
	}

	private void skipColumnIdentifiersLine() throws LineSkipException {
		String columnNamesLine = getNextDocumentLine();
		checkFirstColumnInLine(columnNamesLine, COLUMN_IDENTIFIERS_LINE_EXPECTED_FIRST_COLUMN);
	}

	@Override
	protected String getNextDocumentIdentifier() {
		String separator = Character.toString(CsvCharacters.SEPARATOR);
		return getNextDocumentLine().split(separator)[0];
	}

}
