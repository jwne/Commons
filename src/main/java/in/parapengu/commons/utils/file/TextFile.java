package in.parapengu.commons.utils.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFile {

	private FileWriter writer;
	private PrintWriter printer;

	public TextFile(File file, boolean append) throws IOException {
		this.writer = new FileWriter(file, append);
		this.printer = new PrintWriter(writer);
	}

	public TextFile(File file) throws IOException {
		this(file, true);
	}

	public FileWriter getWriter() {
		return writer;
	}

	public PrintWriter getPrinter() {
		return printer;
	}

	public void line(String line) {
		printer.printf("%s" + "%n" , line);
	}

	public void save() {
		printer.close();
		printer = new PrintWriter(writer);
	}

	public void close() {
		printer.close();
	}

}
