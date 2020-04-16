package scraper.core.processors;

public class CitationProcessorGiven extends CitationProcessor {

	public CitationProcessorGiven(PageProcessor pageProcessor) {
		super(pageProcessor);
	}

	@Override
	protected boolean isGivenCitation() {
		return true;
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=forwardReferencesOrig]";
	}

}
