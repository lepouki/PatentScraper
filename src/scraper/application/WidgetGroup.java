package scraper.application;

import javax.swing.*;
import javax.swing.border.Border;

public class WidgetGroup extends JPanel {

	public WidgetGroup(String title, int padding) {
		Border titleBorder = BorderFactory.createTitledBorder(title);
		Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);

		Border border = BorderFactory.createCompoundBorder(titleBorder, paddingBorder);
		setBorder(border);
	}

}
