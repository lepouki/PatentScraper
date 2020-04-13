package scraper.core.processors;

import scraper.core.*;

public class IdentifierProcessor extends PropertyProcessor {

	private static final String PROPERTY_NAME = "Identifier";

	private String identifier;

	public IdentifierProcessor() {
		super(PROPERTY_NAME);
	}

	@Override
	public void initializeForNextLayer() {
	}

	@Override
	public void processDocument(Document document) {
		identifier = document.identifier;
	}

	@Override
	public String getPropertyData() {
		return identifier;
	}

}
