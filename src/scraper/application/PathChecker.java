package scraper.application;

import java.io.IOException;
import java.nio.file.*;

public class PathChecker {

	public static class EmptyPathException extends IOException {

		public EmptyPathException() {
			super("An empty path was provided");
		}

	}

	public static class NoSuchPathException extends IOException {

		public NoSuchPathException(String filePath) {
			super("No such path: " + '"' + filePath + '"');
		}

	}

	public static void checkExists(String filePath) throws IOException {
		checkIsEmpty(filePath);

		Path path = Paths.get(filePath);
		boolean exists = Files.exists(path);

		if (!exists) {
			throw new NoSuchPathException(filePath);
		}
	}

	private static void checkIsEmpty(String filePath) throws EmptyPathException {
		boolean emptyPath = filePath.isEmpty();

		if (emptyPath) {
			throw new EmptyPathException();
		}
	}

}
