package scraper.core.processors;

import scraper.core.Document;

public class PriorityDateProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "priority date";

	private String priorityDate;

	public PriorityDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		priorityDate = selectFirst("time[itemprop=priorityDate]").ownText();
	}

	@Override
	protected String getPropertyValue() {
		return priorityDate;
	}

}
