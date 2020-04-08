package scraper.application.widgets;

import scraper.core.PropertyRetriever;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRetrieverOptionGrid extends JPanel {

	private static final int COLUMN_COUNT = 4;

	private List<PropertyRetrieverOption> propertyRetrieverOptions;

	public PropertyRetrieverOptionGrid(List<PropertyRetrieverOption> propertyRetrieverOptions) {
		GridLayout layout = new GridLayout(0, COLUMN_COUNT);
		setLayout(layout);

		this.propertyRetrieverOptions = propertyRetrieverOptions;
		pushPropertyRetrieverOptions();
	}

	private void pushPropertyRetrieverOptions() {
		for (PropertyRetrieverOption propertyRetrieverOption : propertyRetrieverOptions) {
			add(propertyRetrieverOption);
		}
	}

	public List<PropertyRetriever> getPropertyRetrievers() {
		List<PropertyRetriever> propertyRetrievers = new ArrayList<>();

		for (PropertyRetrieverOption propertyRetrieverOption : propertyRetrieverOptions) {
			propertyRetrieverOption.pushPropertyRetrieverToList(propertyRetrievers);
		}

		return propertyRetrievers;
	}

}
