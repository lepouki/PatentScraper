package scraper.core.properties;

import org.jsoup.nodes.Element;
import scraper.core.*;

import java.io.IOException;

public class PageScraper extends PropertyScraper {

	private static final String READABLE_NAME = "Document page";
	private static final String PATENT_LINK_PREFIX = "https://patents.google.com/patent/";

	private boolean useNativeLanguage = false;
	private String pageLink;
	private org.jsoup.nodes.Document page;

	public PageScraper() {
		super(READABLE_NAME);
	}

	public void setUseNativeLanguage(boolean useNativeLanguage) {
		this.useNativeLanguage = useNativeLanguage;
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		page = null;
		pageLink = makePageLink(document);
		tryRetrievePage();
	}

	private String makePageLink(Document document) {
		String languageSuffix = useNativeLanguage ? "" : "/en";
		return PATENT_LINK_PREFIX + document.identifier + languageSuffix;
	}

	private void tryRetrievePage() throws NoSuchPropertyException {
		try {
			page = PageDownloader.retrieveDocument(pageLink);
		}
		catch (IOException exception) {
			page = new org.jsoup.nodes.Document("");
			throw new NoSuchPropertyException();
		}
	}

	public Element getPage() {
		return page;
	}

	public String getPageLink() {
		return pageLink;
	}

}
