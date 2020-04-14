package scraper.core.processors;

public abstract class CitationCountProcessor extends IntegerPagePropertyProcessor {

	public CitationCountProcessor(String propertyName, PageProcessor pageProcessor) {
		super(propertyName, pageProcessor);
	}

	@Override
	protected int getValue() throws NoSuchPropertyException {
		String selector = getSelector();
		return select(selector).size();
	}

	protected abstract String getSelector();

}
