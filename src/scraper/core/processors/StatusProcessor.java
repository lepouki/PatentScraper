package scraper.core.processors;

import scraper.core.Document;

public class StatusProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "status";

	private String status;

	public StatusProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) {
		try {
			status = selectFirst("span[itemprop=status]").ownText();
		}
		catch (NoSuchPropertyException exception) {
			status = "Application"; // Patents not yet granted don't have a status element
		}
	}

	@Override
	protected String getPropertyValue() {
		return status;
	}

}
