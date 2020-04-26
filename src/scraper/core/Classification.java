package scraper.core;

public class Classification extends CsvConvertible {

	public String code;
	public String description;

	public Classification(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String[] toCsvEntries() {
		return new String[] {code, description};
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

}
