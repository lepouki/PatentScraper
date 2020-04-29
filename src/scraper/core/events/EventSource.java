package scraper.core.events;

import java.util.*;

public class EventSource {

	private final Set<EventListener> eventListeners;

	public EventSource() {
		eventListeners = new HashSet<>();
	}

	public void pushEventListener(EventListener eventListener) {
		if (eventListener != null) {
			eventListeners.add(eventListener);
		}
	}

	protected void notifyEventListeners(Event event) {
		for (EventListener eventListener : eventListeners) {
			eventListener.onEventReceived(event);
		}
	}

}
