package scraper.core.processors;

import scraper.core.Document;

public class AssigneeProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Current assignee";

	private String currentAssignee;

	public AssigneeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		currentAssignee = selectFirst("dd[itemprop=assigneeCurrent]").ownText();
	}

	@Override
	public String getPropertyData() {
		return currentAssignee;
	}

}
