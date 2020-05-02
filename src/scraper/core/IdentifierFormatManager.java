package scraper.core;

import scraper.core.formatters.DummyIdentifierFormatter;

public class IdentifierFormatManager {

	public static String applyFormat(String identifier) {
		IdentifierFormatter identifierFormatter = getFormatterForIdentifier(identifier);
		return identifierFormatter.applyFormat(identifier);
	}

	private static IdentifierFormatter getFormatterForIdentifier(String identifier) {
		return new DummyIdentifierFormatter();
	}

}
