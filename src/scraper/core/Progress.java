package scraper.core;

public class Progress {

	private float percentage;
	private String nextItemToProcess;

	public Progress(float percentage, String nextItemToProcess) {
		this.percentage = percentage;
		this.nextItemToProcess = nextItemToProcess;
	}

	public float getPercentage() {
		return percentage;
	}

	public String getNextItemToProcess() {
		return nextItemToProcess;
	}

}
