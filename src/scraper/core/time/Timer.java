package scraper.core.time;

public class Timer {

	private Timestamp startTimestamp;

	public Timer() {
		startTimestamp = new Timestamp();
	}

	public void reset() {
		startTimestamp.setToCurrentTime();
	}

	public Timestamp elapsed() {
		long now = System.nanoTime();
		return startTimestamp.durationUntil(now);
	}

}
