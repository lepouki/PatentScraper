package scraper.core.processors;

import org.jsoup.nodes.Element;
import scraper.core.Document;

public class InventorOrAuthorProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Inventor or author";

	private String inventorOrAuthor;

	public InventorOrAuthorProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Element inventorOrAuthorElement = selectFirst("dd[itemprop=inventor]");
		inventorOrAuthor = inventorOrAuthorElement.ownText();
	}

	@Override
	public String getPropertyData() {
		return inventorOrAuthor;
	}

}
