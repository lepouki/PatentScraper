package scraper.application.widgets;

import javax.swing.*;
import java.awt.*;

public class InputOutputChooser extends Group {

	private static final String TITLE = "Input and output";

	private static final String INPUT_FILE_CHOOSER_TOOLTIP = "Input file path";
	private static final String OUTPUT_DIRECTORY_CHOOSER_TOOLTIP = "Output directory path";

	private FileChooser inputFileChooser, outputDirectoryChooser;

	public InputOutputChooser(int padding) {
		super(TITLE, padding);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		inputFileChooser = new FileChooser(
			FileChooser.FileMode.FILES, INPUT_FILE_CHOOSER_TOOLTIP, padding);
		add(inputFileChooser);

		createFileChooserSeparator(padding);

		outputDirectoryChooser = new FileChooser(
			FileChooser.FileMode.DIRECTORIES, OUTPUT_DIRECTORY_CHOOSER_TOOLTIP, padding);
		add(outputDirectoryChooser);
	}

	private void createFileChooserSeparator(int separatorSize) {
		Component separator = Box.createRigidArea(
			new Dimension(0, separatorSize)
		);

		add(separator);
	}

	public String getInputFilePathText() {
		return inputFileChooser.getFilePathText();
	}

	public String getOutputDirectoryPathText() {
		return outputDirectoryChooser.getFilePathText();
	}

}
