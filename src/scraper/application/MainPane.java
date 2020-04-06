package scraper.application;

import javax.swing.*;
import javax.swing.border.Border;

public class MainPane extends JPanel {

	private static final int PADDING = 5;

	public MainPane() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		Border border = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING);
		setBorder(border);
	}

}
