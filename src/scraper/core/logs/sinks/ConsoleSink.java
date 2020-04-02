package scraper.core.logs.sinks;

import scraper.core.logs.Sink;

public class ConsoleSink implements Sink {

	@Override
	public void log(String message) {
		System.out.print(message);
	}

}
