package jhuffman.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OutputFile {
	private BufferedWriter bufferedWriter;

	public OutputFile(String fileName) throws IOException{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

	}
	public void writeText(String text) throws IOException {
		bufferedWriter.write(text);
	}
	public void writeLong(Long a) throws IOException{
		bufferedWriter.write(new String(ByteUtils.longToBytes(a), StandardCharsets.UTF_8));
	}
}
