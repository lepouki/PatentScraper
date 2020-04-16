package scraper;

import scraper.application.Application;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		trySetInterfaceToSystemInterface();
		new Application();
	}

	private static void trySetInterfaceToSystemInterface() {
		try {
			UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName()
			);
		}
		catch (Exception ignored) {}
	}

}
