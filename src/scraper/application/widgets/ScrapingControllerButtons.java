package scraper.application.widgets;

import scraper.application.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrapingControllerButtons extends JPanel implements ActionListener {

	private static final String START_BUTTON_TEXT = "Start";
	private static final String ABORT_BUTTON_TEXT = "Abort";

	private JButton startButton, abortButton;
	private Application application;

	public ScrapingControllerButtons(Application application) {
		createButtons();
		this.application = application;
	}

	private void createButtons() {
		createStartButton();
		createAbortButton();
	}

	private void createStartButton() {
		startButton = new JButton(START_BUTTON_TEXT);

		add(startButton);
		startButton.addActionListener(this);
	}

	private void createAbortButton() {
		abortButton = new JButton(ABORT_BUTTON_TEXT);
		abortButton.setEnabled(false);

		add(abortButton);
		abortButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object sourceButton = event.getSource();
		notifyApplicationButtonPressed(sourceButton);
	}

	private void notifyApplicationButtonPressed(Object sourceButton) {
		if (sourceButton == startButton) {
			application.onStartButtonPressed();
		}
		else if (sourceButton == abortButton) {
			application.onAbortButtonPressed();
		}

		toggleButtons();
	}

	private void toggleButtons() {
		toggleButton(startButton);
		toggleButton(abortButton);
	}

	private void toggleButton(JButton button) {
		boolean isButtonEnabled = button.isEnabled();
		button.setEnabled(!isButtonEnabled);
	}

}
