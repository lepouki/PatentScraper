package scraper.core.processors;

import scraper.core.Document;

public class PublicationDateProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Publication date";

	private String publicationDate;

	public PublicationDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		publicationDate = selectFirst("time[itemprop=publicationDate]").ownText();
	}

	@Override
	public String getPropertyData() {
		return publicationDate;
	}

}
