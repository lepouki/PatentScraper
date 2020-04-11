package scraper.core;

public interface FileWriter {

	void write(String data);

	void openFile(String filePath);

	void close();

}
