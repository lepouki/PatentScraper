package scraper.core.processors;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class OriginalAssigneeProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Original assignee";

	private String originalAssignee;

	public OriginalAssigneeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element originalAssigneeElement = selectFirst("dd[itemprop=assigneeOriginal]");
		originalAssignee = originalAssigneeElement.ownText();
	}

	@Override
	public String getPropertyData() {
		return originalAssignee;
	}

}
