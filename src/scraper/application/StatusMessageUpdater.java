package scraper.application;

import scraper.application.groups.ScraperControls;
import scraper.core.*;

public class StatusMessageUpdater {

	private final ScraperControls scraperControls;
	private String layerStatus;
	private String documentStatus;

	public StatusMessageUpdater(ScraperControls scraperControls) {
		this.scraperControls = scraperControls;
	}

	public void processScraperProgress(ProgressEvent event) {
		if (event instanceof Scraper.LayerProgressEvent) {
			processLayerProgressEvent(event);
		}
		else if (event instanceof Scraper.DocumentProgressEvent) {
			processDocumentProgressEvent(event);
		}

		updateStatusMessage();
	}

	private void processLayerProgressEvent(ProgressEvent event) {
		layerStatus = event.getStatus();
	}

	private void processDocumentProgressEvent(ProgressEvent event) {
		documentStatus = String.format(
			"%s (%s/%s)",
			event.getStatus(), event.getValue(), event.getMaximumValue()
		);
	}

	private void updateStatusMessage() {
		String status = layerStatus + " - " + documentStatus;
		scraperControls.setStatus(status);
	}

}
