package scraper.application.widgets;

import scraper.application.Widget;

import javax.swing.*;
import java.awt.*;

public class OptionGroup extends Widget {

	public OptionGroup(String title) {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		createTitleLabel(title);
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		makeTitleTextBold(titleLabel);
		add(titleLabel, BorderLayout.NORTH);
	}

	private void makeTitleTextBold(JLabel titleLabel) {
		Font titleFont = titleLabel.getFont();

		titleLabel.setFont(
			titleFont.deriveFont(titleFont.getStyle() | Font.BOLD)
		);
	}

	public void setContent(Widget content) {
		add(content, BorderLayout.WEST);
	}

}
