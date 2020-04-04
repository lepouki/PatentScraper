package scraper.core;

public class Progress {

	private float percentage;
	private String lastItemProcessed;

	public Progress(float percentage, String lastItemProcessed) {
		this.percentage = percentage;
		this.lastItemProcessed = lastItemProcessed;
	}

	public float getPercentage() {
		return percentage;
	}

	public String getLastItemProcessed() {
		return lastItemProcessed;
	}

}
