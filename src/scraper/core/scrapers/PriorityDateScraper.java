package scraper.core.scrapers;

import scraper.core.Document;

public class PriorityDateScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Priority date";

	private String priorityDate;

	public PriorityDateScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"priority date"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		priorityDate = selectFirst("time[itemprop=priorityDate]").ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {priorityDate};
	}

}
