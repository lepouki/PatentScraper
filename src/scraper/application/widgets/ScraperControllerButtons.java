package scraper.application.widgets;

import scraper.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScraperControllerButtons extends JPanel implements ActionListener {

	private static final String START_BUTTON_TEXT = "Start";
	private static final String ABORT_BUTTON_TEXT = "Abort";

	private JButton startButton, abortButton;
	private Application application;

	public ScraperControllerButtons(Application application) {
		FlowLayout layout = new FlowLayout();
		setLayout(layout);

		startButton = new JButton(START_BUTTON_TEXT);
		add(startButton);
		startButton.addActionListener(this);

		abortButton = new JButton(ABORT_BUTTON_TEXT);
		abortButton.setEnabled(false);
		add(abortButton);
		abortButton.addActionListener(this);

		this.application = application;
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

		toggleButtons();
	}

	private void notifyApplicationStartButtonPressed() {
		application.onStartButtonPressed();
	}

	private void notifyApplicationAbortButtonPressed() {
		application.onAbortButtonPressed();
	}

	private void toggleButtons() {
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
