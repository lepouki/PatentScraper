package scraper.application.widgets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileChooser extends JPanel implements ActionListener {

	public enum FileMode {

		FILES(JFileChooser.FILES_ONLY),
		DIRECTORIES(JFileChooser.DIRECTORIES_ONLY);

		private int fileSelectionMode;

		FileMode(int fileSelectionMode) {
			this.fileSelectionMode = fileSelectionMode;
		}

		public int getFileSelectionMode() {
			return fileSelectionMode;
		}

	}

	private static final int FILE_PATH_TEXT_COLUMN_COUNT = 45;
	private static final String OPEN_BUTTON_TEXT = "Open";

	private JTextField filePathText;
	private JButton openButton;
	private JFileChooser fileChooser;

	public FileChooser(FileMode fileMode, String tooltip, int buttonSeparatorSize) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		createComponents(fileMode, tooltip);
		applyLayoutConstraints(layout, buttonSeparatorSize);
	}

	private void createComponents(FileMode fileMode, String tooltip) {
		filePathText = new JTextField();
		add(filePathText);
		filePathText.setColumns(FILE_PATH_TEXT_COLUMN_COUNT);
		filePathText.setToolTipText(tooltip);

		openButton = new JButton(OPEN_BUTTON_TEXT);
		add(openButton);
		openButton.addActionListener(this);

		fileChooser = new JFileChooser();
		int fileSelectionMode = fileMode.getFileSelectionMode();
		fileChooser.setFileSelectionMode(fileSelectionMode);
	}

	private void applyLayoutConstraints(SpringLayout layout, int buttonSeparatorSize) {
		applyLayoutConstraintsForFilePathText(layout);
		applyLayoutConstraintsForOpenButton(layout, buttonSeparatorSize);
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

	private void applyLayoutConstraintsForOpenButton(SpringLayout layout, int separatorSize) {
		layout.putConstraint(
			SpringLayout.NORTH, openButton,
			0,
			SpringLayout.NORTH, this
		);

		layout.putConstraint(
			SpringLayout.WEST, openButton,
			separatorSize,
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

	private void updateFilePathText() {
		String chosenFileAbsolutePath = fileChooser.getSelectedFile().getAbsolutePath();
		filePathText.setText(chosenFileAbsolutePath);
	}

	public String getFilePathText() {
		return filePathText.getText();
	}

}
