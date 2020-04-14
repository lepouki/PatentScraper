package scraper.core.processors;

public class CitationCountProcessorReceived extends CitationCountProcessor {

	private static final String PROPERTY_NAME = "Received citation count";

	public CitationCountProcessorReceived(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected String getSelector() {
		return "tr[itemprop=forwardReferencesOrig]";
	}

}
