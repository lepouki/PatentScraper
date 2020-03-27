package scraper.core.events;

public class Event {

	public Event(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

	private Object source;

}
