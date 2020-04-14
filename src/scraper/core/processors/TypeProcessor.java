package scraper.core.processors;

import scraper.core.Document;

public class TypeProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Type";

	private String type;

	public TypeProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		type = selectFirst("meta[itemprop=type]").attr("content");
	}

	@Override
	public String getPropertyData() {
		return type;
	}

}
