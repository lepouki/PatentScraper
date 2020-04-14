package scraper.core.processors;

import scraper.core.Document;

public class PatentOfficeProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "patent office";

	private String patentOffice;

	public PatentOfficeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		patentOffice = selectFirst("dd[itemprop=countryName]").ownText();
	}

	@Override
	protected String getPropertyValue() {
		return patentOffice;
	}

}
