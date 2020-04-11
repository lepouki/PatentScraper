package scraper.application;

import java.awt.event.*;

public class WindowCloseEventListener extends WindowAdapter {

	private final Application application;

	public WindowCloseEventListener(Application application) {
		this.application = application;
	}

	@Override
	public void windowClosing(WindowEvent event) {
		application.abort();
		System.exit(0);
	}

}
