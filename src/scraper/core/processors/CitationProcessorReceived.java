package scraper.core.processors;

public class CitationProcessorReceived extends CitationProcessor {

	public CitationProcessorReceived(PageProcessor pageProcessor) {
		super(pageProcessor);
	}

	@Override
	protected boolean isGivenCitation() {
		return false;
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=backwardReferencesOrig]";
	}

}
