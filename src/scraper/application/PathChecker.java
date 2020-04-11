package scraper.application;

import java.io.IOException;
import java.nio.file.*;

public class PathChecker {

	public static class EmptyPathException extends IOException {

		public EmptyPathException() {
			super("An empty path was provided");
		}

	}

	private static final String NO_SUCH_FILE_ERROR_MESSAGE = "File not found: ";

	public static void checkExists(String filePath) throws IOException {
		checkIsEmpty(filePath);

		Path path = Paths.get(filePath);
		boolean fileExists = Files.exists(path);

		if (!fileExists) {
			throw new NoSuchFileException(NO_SUCH_FILE_ERROR_MESSAGE + filePath);
		}
	}

	private static void checkIsEmpty(String filePath) throws EmptyPathException {
		boolean emptyPath = filePath.isEmpty();

		if (emptyPath) {
			throw new EmptyPathException();
		}
	}

}
