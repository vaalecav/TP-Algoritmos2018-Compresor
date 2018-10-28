package jhuffman;

import java.util.Map;

import jhuffman.util.Compression;
import jhuffman.util.Decompression;
import jhuffman.util.files.FileMode;
import jhuffman.util.files.HuffmanFile;
import jhuffman.util.files.InputFile;
import jhuffman.util.files.OutputFile;

public class Huffman
{	
	public static void main(String[] args) throws Exception
	{
		String filename = "cocorito.bmp";//args[0];
		if( filename.endsWith(".huf") )
		{
			descomprimir(filename);
		}
		else
		{
			comprimir(filename);
		}
	}

	public static void comprimir(String filename) throws Exception
	{
		Map<Character,String> huffmanTree = Compression.getHuffmanForFile(filename);
		String header = Compression.getHuffmanHeader(huffmanTree);
		InputFile inputFile = new InputFile(filename);
		Long originalLenght = inputFile.getLenghtInBytes().longValue();
		String compressedBits = Compression.compressText(huffmanTree, inputFile.getAllChars());
		inputFile.close();
		HuffmanFile huffmanFile = new HuffmanFile(filename + ".huf", FileMode.OUT);
		huffmanFile.setCompressedBits(compressedBits);
		huffmanFile.setOriginalContentLenght(originalLenght);
		huffmanFile.setHeader(header);
		huffmanFile.dump();
	}
	
	public static void descomprimir(String filename) throws Exception
	{
		HuffmanFile huffmanFile = new HuffmanFile(filename, FileMode.IN);
		huffmanFile.retrieve();
		Map<String, Character> huffMantree = Decompression.getHuffmanFromHeader(huffmanFile.getHeader());
		String decompressed = Decompression.decompressText(huffmanFile.getCompressedBits(), huffMantree, huffmanFile.getOriginalContentLenght().intValue());
		OutputFile outputFile = new OutputFile("desc_" + filename.substring(0, filename.length()-4));
		outputFile.writeText(decompressed.toCharArray());
		outputFile.flush();
	}
}
