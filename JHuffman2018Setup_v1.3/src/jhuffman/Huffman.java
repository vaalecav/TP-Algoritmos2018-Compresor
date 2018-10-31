package jhuffman;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jhuffman.util.BitReader;
import jhuffman.util.BitWriter;
import jhuffman.util.Compression;

public class Huffman
{	
	public static void main(String[] args) throws IOException
	{
		String filename = "cocorito.txt.huf";//args[0];
		if( filename.endsWith(".huf") )
		{
			descomprimir(filename);
		}
		else
		{
			comprimir(filename);
		}
	}

	public static void comprimir(String filename) throws IOException
	{
		Map<Character,String> huffmanTree = new HashMap<Character,String>();
		int longitudDelTextoAComprimir = Compression.getMappedTree(filename, huffmanTree);
		String header = Compression.getHuffmanHeader(huffmanTree);
		String compressed = Compression.getCompressedContent(huffmanTree, filename);
		BitWriter writer = new BitWriter(filename+".huf");
		writer.writeBit(header.length());
		for (char c : header.toCharArray()) {
			writer.writeBit(c);
		}
		writer.writeBit(longitudDelTextoAComprimir);
		for (char c : compressed.toCharArray()) {
			writer.writeBit(c);
		}
		writer.close();
	}
	
	public static void descomprimir(String filename) throws IOException
	{
		BitReader reader = new BitReader(filename);
		Map<String, Character> codes = new HashMap<String, Character> ();
		Integer largo = Compression.getCodes(reader, codes);
		String decompressed = Compression.getDecompressedContent(codes, reader, largo);
		System.out.println(decompressed);
		
		BitWriter writer = new BitWriter("desc_" + filename.substring(0, filename.length()-4));
		for (char c : decompressed.toCharArray()) {
			writer.writeBit(c);
		}
		writer.close();
	}
}
