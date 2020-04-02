package scraper.core;

public class Progress {

	private float percentage;

	public Progress(float percentage) {
		this.percentage = percentage;
	}

	public float getPercentage() {
		return percentage;
	}

	/**
	 * Gets overridden by the actual Process classes.
	 */
	public String getLastItemProcessed() {
		return "";
	}

}
