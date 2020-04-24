package scraper.core;

public class Document {

	public String identifier;

	public Document(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public boolean equals(Object other) {
		boolean isDocument = other instanceof Document;

		if (isDocument) {
			Document otherDocument = (Document)other;
			return identifier.equals(otherDocument.identifier);
		}

		return false;
	}

}
