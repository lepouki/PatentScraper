package scraper.core.processors;

public class ClaimCountProcessor extends IntegerPagePropertyProcessor {

	private static final String PROPERTY_NAME = "Claim count";

	public ClaimCountProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected int getValue() throws NoSuchPropertyException {
		return select("li[class=claim]").size();
	}

}
