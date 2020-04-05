package scraper.application.widgets;

import scraper.application.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooser extends JPanel implements ActionListener {

	public enum Mode {

		FILE(JFileChooser.FILES_ONLY),
		DIRECTORY(JFileChooser.DIRECTORIES_ONLY);

		private int selectionMode;

		Mode(int selectionMode) {
			this.selectionMode = selectionMode;
		}

		public int getSelectionMode() {
			return selectionMode;
		}

	}

	private JButton openButton;
	private JLabel fileText;
	private JFileChooser fileChooser;
	private Application application;

	public FileChooser(Application application, Mode mode, String openButtonText) {
		createOpenButton(openButtonText);
		createFileText(mode);
		createFileChooser(mode);
		this.application = application;
	}

	private void createOpenButton(String buttonText) {
		openButton = new JButton(buttonText);

		add(openButton);
		openButton.addActionListener(this);
	}

	private void createFileChooser(Mode mode) {
		fileChooser = new JFileChooser();

		int selectionMode = mode.getSelectionMode();
		fileChooser.setFileSelectionMode(selectionMode);
	}

	private void createFileText(Mode mode) {
		String noFileChosenText = makeNoFileChosenText(mode);
		fileText = new JLabel(noFileChosenText);

		add(fileText);
	}

	private String makeNoFileChosenText(Mode mode) {
		String modeLowercase = mode.toString().toLowerCase();
		return "No " + modeLowercase + " chosen";
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == openButton) {
			if (fileChooser.showOpenDialog(application) == JFileChooser.APPROVE_OPTION) {
				updateFileText();
			}
		}
	}

	private void updateFileText() {
		File file = fileChooser.getSelectedFile();
		String chosenFileAbsolutePath = file.getAbsolutePath();
		fileText.setText(chosenFileAbsolutePath);
	}

	public String getFileAbsolutePath() {
		return fileText.getText();
	}

}
