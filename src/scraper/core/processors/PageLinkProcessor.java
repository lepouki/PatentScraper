package scraper.core.processors;

public class PageLinkProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Page link";

	public PageLinkProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public String getPropertyData() {
		return getPageLink();
	}

}
