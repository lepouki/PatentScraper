package scraper.application.widgets;

import javax.swing.*;

public class OptionsPicker extends JPanel {

	public OptionsPicker(String title) {
		createTitleLabel(title);
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		add(titleLabel);
	}

}
