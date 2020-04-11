package scraper.application.parsers;

import scraper.application.CsvParser;
import scraper.core.*;

import java.io.*;
import java.util.*;

public class CustomCsvParser extends CsvParser {

	private static final String INVALID_FORMAT_ERROR_MESSAGE = "Invalid identifier format: ";

	private Scanner scanner;

	@Override
	protected Set<Document> parse(String filePath) throws FormatException {
		createScanner(filePath);
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
		// The input file path is checked before constructing the parser so this should never happen
		catch (FileNotFoundException ignored) {}
	}

	private Set<Document> parseLines() throws FormatException {
		Set<Document> documents = new HashSet<>();

		while (scanner.hasNextLine()) {
			Document document = new Document();
			document.identifier = processNextDocumentIdentifier();
			documents.add(document);
		}

		return documents;
	}

	private String processNextDocumentIdentifier() throws FormatException {
		String identifier = scanner.nextLine();
		checkIdentifierFormat(identifier);
		return identifier;
	}

	private void checkIdentifierFormat(String identifier) throws FormatException {
		boolean incorrectFormat =
			containsCharacter(identifier, ' ') ||
			containsCharacter(identifier, CsvCharacters.SEPARATOR) ||
			containsCharacter(identifier, CsvCharacters.QUOTE);

		if (incorrectFormat) {
			throw new FormatException(INVALID_FORMAT_ERROR_MESSAGE, identifier);
		}
	}

	private boolean containsCharacter(String identifier, char character) {
		String characterString = Character.toString(character);
		return identifier.contains(characterString);
	}

}
