package scraper.core.logs;

import java.util.HashSet;
import java.util.Set;

public class Logger {

	private Set<Sink> sinks;

	public Logger() {
		sinks = new HashSet<>();
	}

	public boolean hasSink(Sink sink) {
		return sinks.contains(sink);
	}

	public int getSinkCount() {
		return sinks.size();
	}

	public void pushSink(Sink sink) {
		if (sink != null) {
			sinks.add(sink);
		}
	}

	public void popSink(Sink sink) {
		sinks.remove(sink);
	}

	public void log(String format, Object... arguments) {
		String message = String.format(format, arguments);
		log(message);
	}

	public void log(String message) {
		for (Sink sink : sinks) {
			sink.log(message);
		}
	}

}
