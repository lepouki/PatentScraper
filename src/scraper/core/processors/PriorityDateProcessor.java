package scraper.core.processors;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class PriorityDateProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Priority date";

	private String priorityDate;

	public PriorityDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element priorityDateElement = selectFirst("time[itemprop=priorityDate]");
		priorityDate = priorityDateElement.ownText();
	}

	@Override
	public String getPropertyData() {
		return priorityDate;
	}

}
