package scraper.core.processors;

import scraper.core.Document;

public class FilingOrCreationDateProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Filing or creation date";

	private String fileOrCreationDate;

	public FilingOrCreationDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		fileOrCreationDate = selectFirst("time[itemprop=filingDate]").ownText();
	}

	@Override
	public String getPropertyData() {
		return fileOrCreationDate;
	}

}
