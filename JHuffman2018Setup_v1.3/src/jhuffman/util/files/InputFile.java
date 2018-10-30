package jhuffman.util.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class InputFile {

	private Integer currentPos;
	private File file;
	byte[] fileContent;
	boolean eof;


	public InputFile(String filename) throws IOException {
		file = new File(filename);
		fileContent = Files.readAllBytes(file.toPath());
		currentPos = 0;
		eof = false;
	}
	public Integer getLenghtInBytes(){
		return fileContent.length;
	}
	public Integer getLenghtInBits(){
		return fileContent.length*8;
	}
	public String getAllChars(){
		return new String(fileContent, StandardCharsets.ISO_8859_1);
	}

	public byte[] getAllBytes(){
		return fileContent.clone();
	}

	public String getAllBits(){
		return BinaryConverter.stringTobinary(getAllChars());
	}

	public String getNextBits(Integer n){
		String out = "";
		while(n > 0){
			try{
				out = getAllBits().substring(currentPos, currentPos + n);
				break;
			}catch (StringIndexOutOfBoundsException e){
				n--;
			}
		}
		if(out.isEmpty()) eof = true;
		currentPos += n;
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