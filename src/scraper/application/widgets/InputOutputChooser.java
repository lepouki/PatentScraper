package scraper.application.widgets;

import javax.swing.*;

public class InputOutputChooser extends JPanel {

	private static final String INPUT_FILE_CHOOSER_TITLE = "Input file";
	private static final String OUTPUT_DIRECTORY_CHOOSER_TITLE = "Output directory";

	private FileChooserWithTitleLabel inputFileChooser, outputDirectoryChooser;

	public InputOutputChooser() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createFileChoosers();
	}

	private void createFileChoosers() {
		createInputFileChooser();
		createOutputDirectoryChooser();
	}

	private void createInputFileChooser() {
		inputFileChooser = new FileChooserWithTitleLabel(
			INPUT_FILE_CHOOSER_TITLE, FileChooser.FileMode.FILES);

		add(inputFileChooser);
	}

	private void createOutputDirectoryChooser() {
		outputDirectoryChooser = new FileChooserWithTitleLabel(
			OUTPUT_DIRECTORY_CHOOSER_TITLE, FileChooser.FileMode.DIRECTORIES);

		add(outputDirectoryChooser);
	}

	public String getInputFilePath() {
		return inputFileChooser.getPath();
	}

	public String getOutputDirectoryPath() {
		return outputDirectoryChooser.getPath();
	}

}
