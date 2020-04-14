package scraper.core.processors;

import scraper.core.Document;

public class PriorityDateProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Priority date";

	private String priorityDate;

	public PriorityDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		priorityDate = selectFirst("time[itemprop=priorityDate]").ownText();
	}

	@Override
	public String getPropertyData() {
		return priorityDate;
	}

}
