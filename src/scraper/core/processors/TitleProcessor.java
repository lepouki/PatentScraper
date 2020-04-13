package scraper.core.processors;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class TitleProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Title";

	private String title;

	public TitleProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element titleElement = selectFirst("span[itemprop=title]");
		title = titleElement.ownText();
	}

	@Override
	public String getPropertyData() {
		return title;
	}

}
