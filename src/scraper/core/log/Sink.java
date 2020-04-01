package scraper.core.log;

public interface Sink {

	void log(String message);
	void log(String format, Object... arguments);

}
