package scraper.core.processors;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;

public abstract class PagePropertyProcessor extends PropertyProcessor {

	private final PageProcessor pageProcessor;

	public PagePropertyProcessor(PageProcessor pageProcessor) {
		this.pageProcessor = pageProcessor;
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
	}

	@Override
	public String[] getPropertyData() {
		return new String[0];
	}

	protected String getPageLink() {
		return pageProcessor.getPageLink();
	}

	protected Elements select(String selector) throws NoSuchPropertyException {
		Elements selected = pageProcessor.getPage().select(selector);

		if (selected.size() == 0) {
			throw new NoSuchPropertyException();
		}

		return selected;
	}

	protected Element selectFirst(String selector) throws NoSuchPropertyException {
		Element selected = pageProcessor.getPage().selectFirst(selector);

		if (selected == null) {
			throw new NoSuchPropertyException();
		}

		return selected;
	}

}
