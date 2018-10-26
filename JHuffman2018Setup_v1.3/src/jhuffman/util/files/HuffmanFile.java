package jhuffman.util.files;

import java.io.IOException;

public class HuffmanFile {
	private InputFile input;
	private OutputFile output;

	private Long headerLengh = null;
	private Long originalContentLenght = null;
	private String compressedBits = null;
	private String header = null;

	public HuffmanFile(String filename, FileMode fileMode) throws IOException {
		if(fileMode.equals(FileMode.IN)){
			input = new InputFile(filename);
			output = null;
		}else{
			output = new OutputFile(filename);
			input = null;
		}
	}
	public FileMode mode(){
		return input  == null ? FileMode.OUT : FileMode.IN;
	}

	//MODO OUTPUT
	public void setOriginalContentLenght(Long originalContentLenght) throws FileModeMismatchException{
		if(!mode().equals(FileMode.OUT)) throw new FileModeMismatchException();
		this.originalContentLenght = originalContentLenght;
	}
	public void setCompressedBits(String compressedBits) throws FileModeMismatchException{
		if(!mode().equals(FileMode.OUT)) throw new FileModeMismatchException();
		this.compressedBits = compressedBits;
	}
	public void setHeader(String header) throws FileModeMismatchException{
		if(!mode().equals(FileMode.OUT)) throw new FileModeMismatchException();
		this.header = header;
		this.headerLengh = Integer.toUnsignedLong(header.length());
	}
	public void dump() throws FileModeMismatchException, IOException, MissingDataException{
		if(!mode().equals(FileMode.OUT)) throw new FileModeMismatchException();
		if(headerLengh == null
		|| header == null
		|| originalContentLenght == null
		|| compressedBits == null) throw new MissingDataException();
		output.writeLong(headerLengh);
		output.writeLong(originalContentLenght);
		output.writeText(header.toCharArray());
		output.writeText(BinaryConverter.binaryToString(compressedBits));
		output.flush();
	}

	//MODO INPUT
	public void retrieve() throws FileModeMismatchException{
		if(!mode().equals(FileMode.IN)) throw new FileModeMismatchException();
		headerLengh = input.getNextLong();
		originalContentLenght  = input.getNextLong();
		StringBuilder headerBuilder  = new StringBuilder();
		for(int i = 0; i < headerLengh; i++){
			headerBuilder.append(input.getNextChar());
		}
		header = headerBuilder.toString();
		compressedBits = input.getNextBits(input.getLenghtInBits()-input.getCurrentPosition());
	}

	public Long getHeaderLengh() throws FileModeMismatchException{
		if(!mode().equals(FileMode.IN)) throw new FileModeMismatchException();
		return headerLengh;
	}

	public Long getOriginalContentLenght() throws FileModeMismatchException{
		if(!mode().equals(FileMode.IN)) throw new FileModeMismatchException();
		return originalContentLenght;
	}

	public String getCompressedBits() throws FileModeMismatchException{
		if(!mode().equals(FileMode.IN)) throw new FileModeMismatchException();
		return compressedBits;
	}

	public String getHeader() throws FileModeMismatchException{
		if(!mode().equals(FileMode.IN)) throw new FileModeMismatchException();
		return header;
	}
}
