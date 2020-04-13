package scraper.application.parsers;

public class CustomCsvParser extends DocumentIdentifierCsvParser {

	@Override
	protected void prepare() {
	}

	@Override
	protected String getNextDocumentIdentifier() {
		return getNextDocumentLine();
	}

}
