package scraper.core;

public class Document {

	public String identifier;

	public Document(String identifier) {
		this.identifier = removeDashes(identifier);
	}

	private String removeDashes(String identifier) {
		return identifier.replace("-", "");
	}

}
