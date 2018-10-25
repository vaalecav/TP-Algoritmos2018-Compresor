package jhuffman.util.files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputFile {
	private BufferedWriter bufferedWriter;
	StringBuilder stringBuilder;
	List<Byte> bytes;

	public OutputFile(String fileName) throws IOException{
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), StandardCharsets.US_ASCII));
		stringBuilder = new StringBuilder();
		bytes = new ArrayList<>();

	}
	public void writeText(String text){
		stringBuilder.append(text);
		byte[] byt = text.getBytes(StandardCharsets.US_ASCII);
		bytes.addAll(byteToByte(byt));
	}
	public void writeLong(Long a){
		stringBuilder.append(new String(ByteUtils.longToBytes(a), StandardCharsets.US_ASCII));
		bytes.addAll(byteToByte(ByteUtils.longToBytes(a)));
	}
	public void flush() throws IOException {
		bufferedWriter.write(stringBuilder.toString());
		bufferedWriter.close();
	}

	private List<Byte> byteToByte(byte[] in){
		List<Byte> out =  new ArrayList<>();//new ArrayList<>(Arrays.asList(in)).stream().;
		for(byte b : in){
			out.add(b);
		}
		return out;
	}
}
