package scraper.application.parsers;

import scraper.core.CsvCharacters;

import java.util.Arrays;

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
		String documentLink = getDocumentLinkInLine();
		return getIdentifierInDocumentLink(documentLink);
	}

	private String getDocumentLinkInLine() {
		String[] splitDocumentLine = getSplitDocumentLine();
		System.out.println(Arrays.toString(splitDocumentLine));
		final int documentLinkIndex = splitDocumentLine.length - 2;
		return splitDocumentLine[documentLinkIndex];
	}

	private String[] getSplitDocumentLine() {
		String separator = Character.toString(CsvCharacters.SEPARATOR);

		// If we don't add the extra character, the representative figure link gets ignored when empty
		return (getNextDocumentLine() + ' ').split(separator);
	}

	private String getIdentifierInDocumentLink(String documentLink) {
		String linkSeparator = Character.toString('/');
		String[] splitDocumentLink = documentLink.split(linkSeparator);

		final int documentIdentifierIndex = splitDocumentLink.length - 2;
		return splitDocumentLink[documentIdentifierIndex];
	}

}
