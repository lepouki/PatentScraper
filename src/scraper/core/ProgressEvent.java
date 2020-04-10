package scraper.core;

import scraper.core.events.Event;

public class ProgressEvent extends Event {

	private final Progress progress;

	public ProgressEvent(Object source, Progress progress) {
		super(source);
		this.progress = progress;
	}

	public Progress getProgress() {
		return progress;
	}

}
