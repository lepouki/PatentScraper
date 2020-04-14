package scraper.core.processors;

public class PageLinkProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "page link";

	public PageLinkProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	protected String getPropertyValue() {
		return getPageLink();
	}

}
