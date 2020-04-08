package scraper.application.widgets;

import scraper.core.PropertyRetriever;

import java.util.List;

public class PropertyRetrieverOption extends Option<PropertyRetriever> {

	public PropertyRetrieverOption(PropertyRetriever propertyRetriever) {
		super(propertyRetriever.getPropertyName(), propertyRetriever);
	}

	public void pushPropertyRetrieverToList(List<PropertyRetriever> propertyRetrievers) {
		PropertyRetriever propertyRetriever = getValue();
		propertyRetrievers.add(propertyRetriever);
	}

}
