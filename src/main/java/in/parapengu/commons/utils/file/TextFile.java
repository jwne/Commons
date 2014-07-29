package in.parapengu.commons.utils.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFile {

	private File file;
	private boolean append;
	private FileWriter writer;
	private PrintWriter printer;

	public TextFile(File file, boolean append) throws IOException {
		this.file = file;
		this.append = append;
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

	public boolean save() {
		printer.close();
		try {
			writer = new FileWriter(file, append);
			printer = new PrintWriter(writer);
			return true;
		} catch(IOException ex) {
			return false;
		}
	}

	public void close() {
		printer.close();
	}

}
