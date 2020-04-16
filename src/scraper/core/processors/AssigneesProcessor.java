package scraper.core.processors;

import scraper.core.Document;

public class AssigneesProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "assignees";

	private String assignees;

	public AssigneesProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		try {
			retrieveAssignees();
		}
		catch (NoSuchPropertyException exception) {
			retrieveInventorOrAuthor(); // When there is no assignee provided, the assignee is the inventor or author
		}
	}

	private void retrieveAssignees() throws NoSuchPropertyException {
		assignees = selectFirst("dd[itemprop=assigneeCurrent]").ownText();
	}

	private void retrieveInventorOrAuthor() throws NoSuchPropertyException {
		String selector = InventorOrAuthorProcessor.getSelector();
		assignees = selectFirst(selector).ownText();
	}

	@Override
	protected String getPropertyValue() {
		return assignees;
	}

}
