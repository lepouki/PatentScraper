package tests.groups;

import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.events.EventSource;
import tests.Test;
import tests.TestGroup;

public class EventSourceTestGroup implements TestGroup {

	private EventSource eventSource;

	@Override
	public void beginNextTest() {
		eventSource = new EventSource();
	}

	@Override
	public Test[] getTests() {
		return new Test[] {
			new EventSourceHasListenerTest(),
			new EventSourceListenerCountTest(),
			new EventSourcePopListenerTest()
		};
	}

	private class EventSourceListenerCountTest extends Test {

		private EventListener eventListener;

		@Override
		public void run() throws Exception {
			givenAnEventListener();

			whenAddingTheListenerToAnEmptyEventSource();

			thenTheEventSourceMustContainOneListener();
		}

		private void givenAnEventListener() {
			eventListener = new DummyEventListener();
		}

		private void whenAddingTheListenerToAnEmptyEventSource() {
			eventSource.pushEventListener(eventListener);
		}

		private void thenTheEventSourceMustContainOneListener() throws AssertionException {
			assertCondition(
				eventSource.getEventListenerCount() == 1,
				"EventSource ListenerCountTest failed"
			);
		}

	}

	private class EventSourceHasListenerTest extends Test {

		private EventListener eventListener;

		@Override
		public void run() throws Exception {
			givenAnEventListener();

			whenAddingTheListenerToAnEmptyEventSource();

			thenTheEventSourceMustContainTheListener();
		}

		private void givenAnEventListener() {
			eventListener = new DummyEventListener();
		}

		private void whenAddingTheListenerToAnEmptyEventSource() {
			eventSource.pushEventListener(eventListener);
		}

		private void thenTheEventSourceMustContainTheListener() throws AssertionException {
			assertCondition(
				eventSource.hasEventListener(eventListener),
				"EventSource HasListenerTest failed"
			);
		}

	}

	private class EventSourcePopListenerTest extends Test {

		private EventListener eventListener;

		@Override
		public void run() throws AssertionException {
			givenAnEventListener();
			givenAnEventSourceThatContainsOnlyTheListener();

			whenPoppingTheListenerFromTheEventSource();

			thenTheEventSourceMustContainZeroListeners();
		}

		private void givenAnEventListener() {
			eventListener = new DummyEventListener();
		}

		private void givenAnEventSourceThatContainsOnlyTheListener() {
			eventSource.pushEventListener(eventListener);
		}

		private void whenPoppingTheListenerFromTheEventSource() {
			eventSource.popEventListener(eventListener);
		}

		private void thenTheEventSourceMustContainZeroListeners() throws AssertionException {
			assertCondition(
				eventSource.getEventListenerCount() == 0,
				"EventSource PopListenerTest failed"
			);
		}

	}

	private static class DummyEventListener implements EventListener {

		@Override
		public void eventReceived(Event event) {
		}

	}

}
