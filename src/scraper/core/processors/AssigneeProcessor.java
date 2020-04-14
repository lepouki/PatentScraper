package scraper.core.processors;

import scraper.core.Document;

public class AssigneeProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "assignee";

	private String assignee;

	public AssigneeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"assignee"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		assignee = selectFirst("dd[itemprop=assigneeCurrent]").ownText();
	}

	@Override
	protected String getPropertyValue() {
		return assignee;
	}

}
