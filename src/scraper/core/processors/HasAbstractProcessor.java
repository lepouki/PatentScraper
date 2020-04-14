package scraper.core.processors;

public class HasAbstractProcessor extends BooleanPagePropertyProcessor {

	private static final String PROPERTY_NAME = "has abstract";

	public HasAbstractProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		selectFirst("div[class=abstract]");
		return true;
	}

}
