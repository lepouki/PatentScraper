package scraper.application;

import javax.swing.*;
import java.awt.*;

public class RecursivelyToggleableWidget extends JPanel {

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		Component[] children = getComponents();

		for (Component child : children) {
			child.setEnabled(enabled);
		}
	}

}
