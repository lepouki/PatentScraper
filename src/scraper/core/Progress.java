package scraper.core;

public class Progress {

	private final float percentage;
	private final String status;

	public Progress(float percentage, String status) {
		this.percentage = percentage;
		this.status = status;
	}

	public float getPercentage() {
		return percentage;
	}

	public String getStatus() {
		return status;
	}

}
