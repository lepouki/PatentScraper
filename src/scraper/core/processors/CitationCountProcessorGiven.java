package scraper.core.processors;

public class CitationCountProcessorGiven extends CitationCountProcessor {

	private static final String PROPERTY_NAME = "Given citation count";

	public CitationCountProcessorGiven(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected String getSelector() {
		return "tr[itemprop=backwardReferencesOrig]";
	}

}
