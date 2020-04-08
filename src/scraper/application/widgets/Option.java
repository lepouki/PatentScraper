package scraper.application.widgets;

import javax.swing.*;

public class Option<Value> extends JCheckBox {

	private Value value;

	public Option(String text, Value value) {
		setText(text);
		this.value = value;
	}

	public Value getValue() {
		return value;
	}

}
