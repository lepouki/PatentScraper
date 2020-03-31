package scraper.core.events;

import java.util.HashSet;
import java.util.Set;

public class EventSource {

	private Set<EventListener> eventListeners;

	public EventSource() {
		eventListeners = new HashSet<>();
	}

	public boolean hasEventListener(EventListener eventListener) {
		return eventListeners.contains(eventListener);
	}

	public int eventListenerCount() {
		return eventListeners.size();
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
			eventListener.eventReceived(event);
		}
	}

}
