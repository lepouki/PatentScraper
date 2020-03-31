package scraper.core.time;

public class Timestamp {

	private long rawTimeInNanoseconds;

	public Timestamp() {
		setToCurrentTime();
	}

	public Timestamp(long rawTimeInNanoseconds) {
		setRawTimeInNanoseconds(rawTimeInNanoseconds);
	}

	public void setToCurrentTime() {
		long currentTime = System.nanoTime();
		setRawTimeInNanoseconds(currentTime);
	}

	public void setRawTimeInNanoseconds(long rawTimeInNanoseconds) {
		this.rawTimeInNanoseconds = rawTimeInNanoseconds;
	}

	public double toSeconds() {
		return (double)rawTimeInNanoseconds / 1e9;
	}

	public Timestamp durationUntil(long durationEnd) {
		long duration = durationEnd - this.rawTimeInNanoseconds;
		return new Timestamp(duration);
	}

}
