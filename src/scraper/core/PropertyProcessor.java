package scraper.core;

public class PropertyProcessor {

	public static class NoSuchPropertyException extends Exception {}

	private Scraper scraper;

	public void setScraper(Scraper scraper) {
		this.scraper = scraper;
	}

	public String[] getPropertyNames() {
		return new String[0];
	}

	public void processDocument(Document document) throws NoSuchPropertyException {
	}

	public String[] getPropertyData() {
		return new String[0];
	}

	protected void pushNextLayerDocument(Document document) {
		scraper.pushNextLayerDocument(document);
	}

}
