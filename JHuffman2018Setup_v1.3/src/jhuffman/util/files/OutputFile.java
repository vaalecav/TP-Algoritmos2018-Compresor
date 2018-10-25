package jhuffman.util.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OutputFile {
	private BufferedWriter bufferedWriter;
	String stringBuilder;

	public OutputFile(String fileName) throws IOException{
		bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		stringBuilder = "";

	}
	public void writeText(String text){
		stringBuilder+=text;
	}
	public void writeLong(Long a){
		stringBuilder += new String(ByteUtils.longToBytes(a), StandardCharsets.UTF_8);
	}
	public void flush() throws IOException {
		bufferedWriter.write(stringBuilder);
		bufferedWriter.close();
	}
}
