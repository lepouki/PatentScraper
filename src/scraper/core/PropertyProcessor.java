package scraper.core;

public abstract class PropertyProcessor {

	public static class NoSuchPropertyException extends Exception {}

	private Scraper scraper;

	public void setScraper(Scraper scraper) {
		this.scraper = scraper;
	}

	public void initializeForNextLayer() {
	}

	public abstract String[] getPropertyNames();

	public abstract void processDocument(Document document) throws NoSuchPropertyException;

	public abstract String[] getPropertyData();

	protected void pushNextLayerDocument(Document document) {
		scraper.pushNextLayerDocument(document);
	}

}
