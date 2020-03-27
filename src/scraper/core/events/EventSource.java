package scraper.core.events;

import java.util.HashSet;
import java.util.Set;

public class EventSource {

	public EventSource() {
		eventListeners = new HashSet<>();
	}

	public boolean hasEventListener(EventListener eventListener) {
		return eventListeners.contains(eventListener);
	}

	public void addEventListener(EventListener eventListener) {
		if (eventListener != null) {
			eventListeners.add(eventListener);
		}
	}

	public void removeEventListener(EventListener eventListener) {
		eventListeners.remove(eventListener);
	}

	protected void notifyEventListeners(Event event) {
		for (EventListener eventListener : eventListeners) {
			eventListener.eventReceived(event);
		}
	}

	private Set<EventListener> eventListeners;

}
