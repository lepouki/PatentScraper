package scraper.core.processors;

public class CitationCountProcessorNonPatent extends CitationCountProcessor {

	private static final String PROPERTY_NAME = "Non patent citation count";

	public CitationCountProcessorNonPatent(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected String getCitationSelector() {
		return "tr[itemprop=detailedNonPatentLiterature]";
	}

}
