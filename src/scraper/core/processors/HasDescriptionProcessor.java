package scraper.core.processors;

public class HasDescriptionProcessor extends BooleanPagePropertyProcessor {

	private static final String PROPERTY_NAME = "Has description";

	public HasDescriptionProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected boolean getValue() throws NoSuchPropertyException {
		selectFirst("section[itemprop=description]");
		return true;
	}

}
