package scraper.core.scrapers;

import scraper.core.Document;

public class AssigneesScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Assignees";

	private String assignees;

	public AssigneesScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"assignees"};
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
		String selector = InventorOrAuthorScraper.getInventorSelector();
		assignees = selectFirst(selector).ownText();
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {assignees};
	}

}
