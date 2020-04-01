package scraper.core.log.sinks;

import scraper.core.log.Sink;

public class ConsoleSink implements Sink {

	@Override
	public void log(String message) {
		System.out.print(message);
	}

	@Override
	public void log(String format, Object... arguments) {
		System.out.printf(format, arguments);
	}

}
