package scraper.core;

import scraper.core.events.Event;

public class ProgressEvent extends Event {

	private final float percentage;
	private final String status;

	public ProgressEvent(Object source, float percentage, String status) {
		super(source);
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
