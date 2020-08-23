package scraper.application.parsers;

import scraper.application.CsvParser;
import scraper.core.*;

import java.io.*;
import java.util.*;

public abstract class DocumentIdentifierCsvParser implements CsvParser {

	public static class IdentifierFormatException extends FormatException {

		public IdentifierFormatException(String identifier) {
			super("Invalid identifier \"" + identifier + "\"");
		}

	}

	private Scanner scanner;

	@Override
	public List<Document> parseDocuments(String filePath) throws FormatException {
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

	private List<Document> parseLines() throws IdentifierFormatException {
		List<Document> documents = new ArrayList<>();

		while (
			scanner.hasNextLine()
		) {
			String identifier = getNextDocumentIdentifier();

			if (
				identifier.isEmpty()
			) continue;

			checkIdentifierCharacters(identifier);
			pushDocumentToDocuments(identifier, documents);
		}

		return documents;
	}

	private void checkIdentifierCharacters(String identifier) throws IdentifierFormatException {
		boolean hasIncorrectCharacters =
			containsCharacter(identifier, ' ') ||
			containsCharacter(identifier, CsvCharacters.SEPARATOR) ||
			containsCharacter(identifier, CsvCharacters.QUOTE);

		if (hasIncorrectCharacters) {
			throw new IdentifierFormatException(identifier);
		}
	}

	private boolean containsCharacter(String identifier, char character) {
		String characterString = Character.toString(character);
		return identifier.contains(characterString);
	}

	private void pushDocumentToDocuments(String identifier, List<Document> documents) {
		documents.add(
			new Document(identifier)
		);
	}

	protected String getNextDocumentLine() {
		return scanner.nextLine();
	}

	protected abstract void prepare() throws FormatException;

	protected abstract String getNextDocumentIdentifier();

}
