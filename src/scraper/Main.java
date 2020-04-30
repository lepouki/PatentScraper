package scraper;

import scraper.application.Application;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		trySetInterfaceToCrossPlatformInterface();
		new Application();
	}

	private static void trySetInterfaceToCrossPlatformInterface() {
		try {
			UIManager.setLookAndFeel(
				UIManager.getCrossPlatformLookAndFeelClassName()
			);
		}
		catch (Exception ignored) {}
	}

}
