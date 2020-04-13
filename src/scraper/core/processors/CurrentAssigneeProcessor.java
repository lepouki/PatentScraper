package scraper.core.processors;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class CurrentAssigneeProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Current Assignee";

	private String currentAssignee;

	public CurrentAssigneeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element currentAssigneeElement = selectFirst("dd[itemprop=assigneeCurrent]");
		currentAssignee = currentAssigneeElement.ownText();
	}

	@Override
	public String getPropertyData() {
		return currentAssignee;
	}

}
