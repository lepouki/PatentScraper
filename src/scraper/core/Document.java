package scraper.core;

public class Document extends HashCodeComparable {

	public String identifier;

	public Document(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

}
