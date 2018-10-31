package jhuffman.util.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class InputFile {

	private Integer currentPos;
	private File file;
	byte[] fileContent;
	InputStream inputStream;
	String bitsBuffer;
	boolean eof;


	public InputFile(String filename) throws IOException {
		file = new File(filename);
		fileContent = null;//Files.readAllBytes(file.toPath());
		inputStream = new FileInputStream(file);
		currentPos = 0;
		bitsBuffer = "";
		eof = false;
	}
	public Integer getLenghtInBytes(){
		return (int) file.length();
	}
	public Integer getLenghtInBits(){
		return (int) file.length()*8;
	}
	public String getAllChars(){
		return bytesToString(fileContent);
	}
	public String bytesToString(byte[] bytes){
		return new String(bytes, StandardCharsets.ISO_8859_1);
	}

	public byte[] getAllBytes(){
		return fileContent.clone();
	}

	public String getAllBits(){
		return BinaryConverter.stringTobinary(getAllChars());
	}

	public String getNextBits(Integer n){
		String out = "";
		while(n > bitsBuffer.length()){
			byte[] bytes = new byte[1];
			try{
				if(inputStream.read(bytes) == 0){
					eof = true;
					return null;
				}
				String bits = BinaryConverter.stringTobinary(bytesToString(bytes));
				bitsBuffer += bits;
			}catch (Exception e){
				break;
			}
		}
		currentPos += n;
		out = bitsBuffer.substring(0,n);
		bitsBuffer = bitsBuffer.substring(n);
		return out;
	}

	public Long getNextLong(){
		String bits = getNextBits(8*8);
		char[] chars = BinaryConverter.binaryToString(bits);
		byte[] bytes = new byte[chars.length];
		for(int i = 0; i < bytes.length; i++){
			bytes[i] = (byte) chars[i];
		}
		return ByteUtils.bytesToLong(bytes);
	}
	public void close(){
		fileContent = null;
		file = null;
		currentPos = null;
		System.gc();
	}

	public Character getNextChar(){
		try{
			return BinaryConverter.binaryToString(getNextBits(8))[0];
		}catch (ArrayIndexOutOfBoundsException e){
			eof = true;
			return null;
		}
	}
	public Integer getCurrentPosition(){
		return currentPos;
	}
	public void resetPosition(){
		currentPos = 0;
		eof = false;
	}
	public Boolean eof(){
		return getCurrentPosition() >= getLenghtInBits() || eof;
	}
}