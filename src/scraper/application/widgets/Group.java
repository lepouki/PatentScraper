package scraper.application.widgets;

import javax.swing.*;
import javax.swing.border.Border;

public class Group extends JPanel {

	public Group(String title, int padding) {
		Border titleBorder = BorderFactory.createTitledBorder(title);
		Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);

		Border border = BorderFactory.createCompoundBorder(titleBorder, paddingBorder);
		setBorder(border);
	}

}
