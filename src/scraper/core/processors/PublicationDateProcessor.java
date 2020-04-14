package scraper.core.processors;

import scraper.core.Document;

public class PublicationDateProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "publication date";

	private String publicationDate;

	public PublicationDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		publicationDate = selectFirst("time[itemprop=publicationDate]").ownText();
	}

	@Override
	protected String getPropertyValue() {
		return publicationDate;
	}

}
