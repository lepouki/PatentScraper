package scraper.application;

import javax.swing.*;
import java.awt.*;

public class Widget extends JPanel {

	@Override
	public void setEnabled(boolean enabled) {
		Component[] children = getComponents();

		for (Component child : children) {
			child.setEnabled(enabled);
		}

		super.setEnabled(enabled);
	}

	protected void createComponentSeparator() {
		Component separator = Box.createRigidArea(
			new Dimension(0, LayoutConfiguration.PADDING)
		);

		add(separator);
	}

}
