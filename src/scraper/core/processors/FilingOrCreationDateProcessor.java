package scraper.core.processors;

import scraper.core.Document;

public class FilingOrCreationDateProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "filing or creation date";

	private String filingOrCreationDate;

	public FilingOrCreationDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		filingOrCreationDate = selectFirst("time[itemprop=filingDate]").ownText();
	}

	@Override
	protected String getPropertyValue() {
		return filingOrCreationDate;
	}

}
