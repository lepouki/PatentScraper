package scraper.core.events;

import java.util.HashSet;
import java.util.Set;

public class EventSource {

	private Set<EventListener> eventListeners;

	public EventSource() {
		eventListeners = new HashSet<>();
	}

	public void pushEventListener(EventListener eventListener) {
		if (eventListener != null) {
			eventListeners.add(eventListener);
		}
	}

	public void popEventListener(EventListener eventListener) {
		eventListeners.remove(eventListener);
	}

	protected void notifyEventListeners(Event event) {
		for (EventListener eventListener : eventListeners) {
			eventListener.onEventReceived(event);
		}
	}

}
