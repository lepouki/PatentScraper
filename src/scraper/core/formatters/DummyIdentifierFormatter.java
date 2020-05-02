package scraper.core.formatters;

import scraper.core.IdentifierFormatter;

public class DummyIdentifierFormatter implements IdentifierFormatter {

	@Override
	public String applyFormat(String identifier) {
		return identifier;
	}

}
