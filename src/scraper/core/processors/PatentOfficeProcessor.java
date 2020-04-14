package scraper.core.processors;

import scraper.core.Document;

public class PatentOfficeProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Patent office";

	private String patentOffice;

	public PatentOfficeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		patentOffice = selectFirst("dd[itemprop=countryName]").ownText();
	}

	@Override
	public String getPropertyData() {
		return patentOffice;
	}

}
