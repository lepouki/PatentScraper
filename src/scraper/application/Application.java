package scraper.application;

import scraper.core.ProgressEvent;

import javax.swing.JFrame;

public class Application extends JFrame {

	public Application() {
		super("Scraper");

		setSize(500, 700);
		setResizable(false);

		// Unsafe -> need to wait for the worker to complete
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public void onProgressMade(ProgressEvent event) {
	}

}
