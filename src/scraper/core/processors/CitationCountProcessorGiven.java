package scraper.core.processors;

public class CitationCountProcessorGiven extends CitationCountProcessor {

	private static final String PROPERTY_NAME = "Given citation count";

	public CitationCountProcessorGiven(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=backwardReferencesOrig]";
	}

}
