package jhuffman.util.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class InputFile {

	private Integer currentPos;
	private File file;
	byte[] fileContent;
	private String bitContent;


	public InputFile(String filename) throws IOException {
		file = new File(filename);
		fileContent = Files.readAllBytes(file.toPath());
		currentPos = 0;
		String aux = getAllChars();
		bitContent = BinaryConverter.stringTobinary(aux);
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
		return bitContent;
	}

	public String getNextBits(Integer n){
		String out = bitContent.substring(currentPos, currentPos + n);
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

	public Character getNextChar(){
		return BinaryConverter.binaryToString(getNextBits(8))[0];
	}
	public Integer getCurrentPosition(){
		return currentPos;
	}
	public void resetPosition(){
		currentPos = 0;
	}
	public Boolean eof(){
		return getCurrentPosition().equals(getLenghtInBits());
	}
}