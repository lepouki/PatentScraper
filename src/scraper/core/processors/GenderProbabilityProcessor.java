package scraper.core.processors;

import scraper.core.Document;
import scraper.core.PropertyProcessor;

public class GenderProbabilityProcessor extends PropertyProcessor {

	private static final String PROPERTY_NAME = "Gender probability";

	private final GenderProcessor genderProcessor;

	public GenderProbabilityProcessor(GenderProcessor genderProcessor) {
		super(PROPERTY_NAME);
		this.genderProcessor = genderProcessor;
	}

	@Override
	public void initializeForNextLayer() {
	}

	@Override
	public void processDocument(Document document) {
	}

	@Override
	public String getPropertyData() {
		float genderProbability = genderProcessor.getGenderProbability();
		return Float.toString(genderProbability);
	}

}
