package scraper.core.processors;

import scraper.core.*;

public class DummyProcessor extends PropertyProcessor {

	@Override
	public String[] getPropertyNames() {
		return new String[0];
	}

	@Override
	public void processDocument(Document document) {
	}

	@Override
	public String[] getPropertyData() {
		return new String[0];
	}

}
