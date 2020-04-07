package scraper.application;

import javax.swing.*;
import javax.swing.border.Border;

public class MainPane extends JPanel {

	public MainPane() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		int padding = ComponentConfiguration.PADDING;
		Border border = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
		setBorder(border);
	}

}
