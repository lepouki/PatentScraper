package scraper.application.widgets;

import scraper.core.PropertyRetriever;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PropertyRetrieverOptionGroup extends JPanel {

	private PropertyRetrieverOptionGrid propertyRetrieverOptionGrid;

	public PropertyRetrieverOptionGroup(String title, List<PropertyRetrieverOption> propertyRetrieverOptions) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createTitleLabel(title);
		this.propertyRetrieverOptionGrid = new PropertyRetrieverOptionGrid(propertyRetrieverOptions);
		add(this.propertyRetrieverOptionGrid);
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		add(titleLabel);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public List<PropertyRetriever> getPropertyRetrievers() {
		return propertyRetrieverOptionGrid.getPropertyRetrievers();
	}

}
