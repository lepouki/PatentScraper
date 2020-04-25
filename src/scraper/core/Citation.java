package scraper.core;

public class Citation extends CsvConvertible {

	public String source, target;
	public String origin;

	public Citation(String source, String target, char originCharacter) {
		this.source = source;
		this.target = target;
		origin = processOriginCharacter(originCharacter);
	}

	private String processOriginCharacter(char originCharacter) {
		switch (originCharacter) {
			case '*': return "examiner";
			case '†': return "third party";
			case '‡': return "family to family";
			 default: return "document";
		}
	}

	@Override
	public String[] toCsvEntries() {
		return new String[] {source, target, origin};
	}

}
