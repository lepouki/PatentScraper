package scraper.application.widgets;

import scraper.application.Application;

import javax.swing.*;

public class InputOutputChooser extends JPanel {

	private static final String INPUT_FILE_CHOOSER_OPEN_BUTTON_TEXT = "Choose input file";
	private static final String OUTPUT_DIRECTORY_CHOOSER_OPEN_BUTTON_TEXT = "Choose output directory";

	private FileChooser inputFileChooser, outputDirectoryChooser;
	private Application application;

	public InputOutputChooser(Application application) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createInputFileChooser();
		createOutputDirectoryChooser();
		this.application = application;
	}

	private void createInputFileChooser() {
		inputFileChooser = new FileChooser(
			application, FileChooser.Mode.FILE, INPUT_FILE_CHOOSER_OPEN_BUTTON_TEXT
		);

		add(inputFileChooser);
	}

	private void createOutputDirectoryChooser() {
		outputDirectoryChooser = new FileChooser(
			application, FileChooser.Mode.DIRECTORY, OUTPUT_DIRECTORY_CHOOSER_OPEN_BUTTON_TEXT
		);

		add(outputDirectoryChooser);
	}

	public String getInputFileAbsolutePath() {
		return inputFileChooser.getFileAbsolutePath();
	}

	public String getOutputDirectoryAbsolutePath() {
		return outputDirectoryChooser.getFileAbsolutePath();
	}

}
