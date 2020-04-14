package scraper.application.parsers;

import scraper.application.CsvParser;
import scraper.core.*;

import java.io.*;
import java.util.*;

public abstract class DocumentIdentifierCsvParser extends CsvParser {

	public static class IdentifierFormatException extends FormatException {

		public IdentifierFormatException(String identifier) {
			super("Invalid identifier \"" + identifier + "\"");
		}

	}

	private Scanner scanner;

	@Override
	public Set<Document> parseFile(String filePath) throws FormatException {
		createScanner(filePath);
		prepare();
		return parseLines();
	}

	private void createScanner(String filePath) {
		File inputFile = new File(filePath);
		tryCreateScanner(inputFile);
	}

	private void tryCreateScanner(File file) {
		try {
			scanner = new Scanner(file);
		}
		// The input file path gets checked before constructing the parser so this should never happen
		catch (FileNotFoundException ignored) {}
	}

	private Set<Document> parseLines() throws IdentifierFormatException {
		Set<Document> documents = new HashSet<>();

		while (scanner.hasNextLine()) {
			String identifier = getNextDocumentIdentifier();

			boolean emptyIdentifier = identifier.isEmpty();
			if (emptyIdentifier) continue;

			checkIdentifierFormat(identifier);
			Document document = new Document(identifier);
			documents.add(document);
		}

		return documents;
	}

	private void checkIdentifierFormat(String identifier) throws IdentifierFormatException {
		boolean incorrectFormat =
			containsCharacter(identifier, ' ') ||
			containsCharacter(identifier, CsvCharacters.SEPARATOR) ||
			containsCharacter(identifier, CsvCharacters.QUOTE);

		if (incorrectFormat) {
			throw new IdentifierFormatException(identifier);
		}
	}

	private boolean containsCharacter(String identifier, char character) {
		String characterString = Character.toString(character);
		return identifier.contains(characterString);
	}

	protected String getNextDocumentLine() {
		return scanner.nextLine();
	}

	protected abstract void prepare() throws FormatException;

	protected abstract String getNextDocumentIdentifier();

}
