package jhuffman.util.files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OutputFile {
	private BufferedWriter bufferedWriter;
	StringBuilder stringBuilder;
	List<Byte> bytes;
	char[] chars;

	public OutputFile(String fileName) throws IOException{
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), StandardCharsets.ISO_8859_1));
		stringBuilder = new StringBuilder();
		bytes = new ArrayList<>();
		chars = new char[0];
	}
	public void writeText(char[] text){
		stringBuilder.append(text);
		chars = concatCharArrays(chars, text);
	}
	public void writeLong(Long a){
		chars = concatCharArrays(chars, new String(ByteUtils.longToBytes(a), StandardCharsets.ISO_8859_1).toCharArray());
	}
	public void flush() throws IOException {
		bufferedWriter.write(chars);
		bufferedWriter.close();
	}

	private List<Byte> byteToByte(byte[] in){
		List<Byte> out =  new ArrayList<>();//new ArrayList<>(Arrays.asList(in)).stream().;
		for(byte b : in){
			out.add(b);
		}
		return out;
	}

	private char[] concatCharArrays(char[] a, char[] b){
		char[] c = new char[a.length + b.length];
		int i = 0;
		while(i < a.length){
			c[i] = a[i];
			i++;
		}
		while(i-a.length < b.length){
			c[i] = b[i-a.length];
			i++;
		}
		return c;
	}
}
