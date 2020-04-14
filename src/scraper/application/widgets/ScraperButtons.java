package scraper.application.widgets;

import scraper.application.*;

import java.awt.event.*;
import javax.swing.*;

public class ScraperButtons extends Widget implements ActionListener {

	private static final String START_BUTTON_TEXT = "Start";
	private static final String ABORT_BUTTON_TEXT = "Abort";

	private JButton startButton, abortButton;
	private final Application application;

	public ScraperButtons(Application application) {
		createButtons();
		this.application = application;

		resetButtons();
	}

	private void createButtons() {
		startButton = createButton(START_BUTTON_TEXT);
		abortButton = createButton(ABORT_BUTTON_TEXT);
	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		add(button);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object sourceButton = event.getSource();
		notifyApplicationButtonPressed(sourceButton);
	}

	private void notifyApplicationButtonPressed(Object sourceButton) {
		if (sourceButton == startButton) {
			notifyApplicationStartButtonPressed();
		}
		else if (sourceButton == abortButton) {
			notifyApplicationAbortButtonPressed();
		}
	}

	private void notifyApplicationStartButtonPressed() {
		application.onStartButtonPressed();
	}

	private void notifyApplicationAbortButtonPressed() {
		application.onAbortButtonPressed();
	}

	public void toggleButtons() {
		toggleButton(startButton);
		toggleButton(abortButton);
	}

	private void toggleButton(JButton button) {
		boolean isButtonEnabled = button.isEnabled();
		button.setEnabled(!isButtonEnabled);
	}

	public void resetButtons() {
		startButton.setEnabled(true);
		abortButton.setEnabled(false);
	}

}
