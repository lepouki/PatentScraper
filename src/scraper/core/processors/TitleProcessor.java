package scraper.core.processors;

import scraper.core.Document;

public class TitleProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "title";

	private String title;

	public TitleProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		title = selectFirst("span[itemprop=title]").ownText();
	}

	@Override
	protected String getPropertyValue() {
		return title;
	}

}
