package scraper.application.widgets;

import javax.swing.*;
import java.awt.*;
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

	private static final int COLUMN_COUNT = 50;
	private static final String OPEN_BUTTON_TEXT = "Open";

	private JButton openButton;
	private JFileChooser fileChooser;
	private JTextField pathText;

	public FileChooser(FileMode fileMode) {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		createOpenButton();
		createFileChooser(fileMode);
		createPathText();
	}

	private void createOpenButton() {
		openButton = new JButton(OPEN_BUTTON_TEXT);

		add(openButton, BorderLayout.WEST);
		openButton.addActionListener(this);
	}

	private void createFileChooser(FileMode fileMode) {
		fileChooser = new JFileChooser();

		int fileSelectionMode = fileMode.getFileSelectionMode();
		fileChooser.setFileSelectionMode(fileSelectionMode);
	}

	private void createPathText() {
		pathText = new JTextField();
		pathText.setColumns(COLUMN_COUNT);

		add(pathText, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				updatePathText();
			}
		}
	}

	private void updatePathText() {
		String chosenFilePath = fileChooser.getSelectedFile().getAbsolutePath();
		pathText.setText(chosenFilePath);
	}

	public String getPath() {
		return pathText.getText();
	}

}
