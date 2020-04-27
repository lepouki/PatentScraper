package scraper.application.widgets;

import scraper.application.*;

import java.awt.event.*;
import java.util.Set;
import javax.swing.*;

public class FileChooser extends Widget implements ActionListener {

	public enum FileMode {

		FILES(JFileChooser.FILES_ONLY),
		DIRECTORIES(JFileChooser.DIRECTORIES_ONLY);

		private final int fileSelectionMode;

		FileMode(int fileSelectionMode) {
			this.fileSelectionMode = fileSelectionMode;
		}

		public int getFileSelectionMode() {
			return fileSelectionMode;
		}

	}

	private static final int FILE_PATH_TEXT_COLUMN_COUNT = 45;
	private static final String BASE_DIRECTORY = "./";
	private static final String OPEN_BUTTON_TEXT = "Open";

	private JTextField filePathText;
	private JButton openButton;
	private JFileChooser fileChooser;

	public FileChooser(FileMode fileMode, String dialogTitle) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		createComponents(fileMode, dialogTitle);
		applyLayoutConstraints(layout);
	}

	private void createComponents(FileMode fileMode, String dialogTitle) {
		filePathText = new JTextField(FILE_PATH_TEXT_COLUMN_COUNT);
		add(filePathText);

		openButton = new JButton(OPEN_BUTTON_TEXT);
		add(openButton);
		openButton.addActionListener(this);

		fileChooser = new JFileChooser(BASE_DIRECTORY);
		fileChooser.setDialogTitle(dialogTitle);
		int fileSelectionMode = fileMode.getFileSelectionMode();
		fileChooser.setFileSelectionMode(fileSelectionMode);
	}

	private void applyLayoutConstraints(SpringLayout layout) {
		applyLayoutConstraintsForFilePathText(layout);
		applyLayoutConstraintsForOpenButton(layout);
		applyLayoutConstraintsForThisContainer(layout);
	}

	private void applyLayoutConstraintsForFilePathText(SpringLayout layout) {
		layout.putConstraint(
			SpringLayout.NORTH, filePathText,
			0,
			SpringLayout.NORTH, this
		);

		layout.putConstraint(
			SpringLayout.WEST, filePathText,
			0,
			SpringLayout.WEST, this
		);

		layout.putConstraint(
			SpringLayout.SOUTH, filePathText,
			0,
			SpringLayout.SOUTH, this
		);
	}

	private void applyLayoutConstraintsForOpenButton(SpringLayout layout) {
		layout.putConstraint(
			SpringLayout.NORTH, openButton,
			0,
			SpringLayout.NORTH, this
		);

		layout.putConstraint(
			SpringLayout.WEST, openButton,
			LayoutConfiguration.PADDING,
			SpringLayout.EAST, filePathText
		);
	}

	private void applyLayoutConstraintsForThisContainer(SpringLayout layout) {
		layout.putConstraint(
			SpringLayout.SOUTH, this,
			0,
			SpringLayout.SOUTH, openButton
		);

		layout.putConstraint(
			SpringLayout.EAST, this,
			0,
			SpringLayout.EAST, openButton
		);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			updateFilePathText();
		}
	}

	public String getFilePathText() {
		return filePathText.getText();
	}

	protected void updateFilePathText() {
		String chosenFileAbsolutePath = fileChooser.getSelectedFile().getAbsolutePath();
		setFilePathText(chosenFileAbsolutePath);
	}

	protected void setFilePathText(String text) {
		filePathText.setText(text);
	}

}
