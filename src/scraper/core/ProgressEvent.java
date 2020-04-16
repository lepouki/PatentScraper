package scraper.core;

import scraper.core.events.Event;

public class ProgressEvent extends Event {

	private final int value;
	private final String status;

	public ProgressEvent(Object source, int value, String status) {
		super(source);
		this.value = value;
		this.status = status;
	}

	public int getValue() {
		return value;
	}

	public String getStatus() {
		return status;
	}

}
