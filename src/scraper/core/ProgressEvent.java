package scraper.core;

import scraper.core.events.Event;

public class ProgressEvent extends Event {

	private final int value;
	private final int maximumValue;
	private final String status;

	public ProgressEvent(Object source, int value, int maximumValue, String status) {
		super(source);
		this.value = value;
		this.maximumValue = maximumValue;
		this.status = status;
	}

	public int getPercentage() {
		return (int)(value / (float)maximumValue * 100.0f);
	}

	public int getValue() {
		return value;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public String getStatus() {
		return status;
	}

}
