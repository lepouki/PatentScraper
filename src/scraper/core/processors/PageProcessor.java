package scraper.core.processors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import scraper.core.*;

import java.io.IOException;

public class PageProcessor extends PropertyProcessor {

	private static final String PATENT_LINK_PREFIX = "https://patents.google.com/patent/";

	private String pageLink = "";
	private org.jsoup.nodes.Document document;

	@Override
	public String[] getPropertyNames() {
		return new String[0];
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		this.document = null;
		pageLink = makePageLink(document);
		tryRetrievePage();
	}

	private String makePageLink(Document document) {
		return PATENT_LINK_PREFIX + document.identifier + "/en";
	}

	private void tryRetrievePage() throws NoSuchPropertyException {
		try {
			document = Jsoup.connect(pageLink).get();
		}
		catch (IOException exception) {
			document = new org.jsoup.nodes.Document("");
			throw new NoSuchPropertyException();
		}
	}

	@Override
	public String[] getPropertyData() {
		return new String[0];
	}

	public Element getPage() {
		return document;
	}

	public String getPageLink() {
		return pageLink;
	}

}
