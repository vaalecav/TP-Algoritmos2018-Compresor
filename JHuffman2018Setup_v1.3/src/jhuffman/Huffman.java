package jhuffman;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import jhuffman.util.BitReader;
import jhuffman.util.BitWriter;
import jhuffman.util.Compression;
import jhuffman.util.Decompression;

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
		File file = new File (filename);
		Map<Character,String> huffmanTree = Compression.getMappedTree(filename);
		String header = Compression.getHuffmanHeader(huffmanTree);

		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String compressed = Compression.compressText(huffmanTree, bufferedReader.readLine());


		String encabezadoTotal = header + "\n" + file.length() + "\n"; //le concateno la longitud del archivo

		BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".huf"));
		writer.write(encabezadoTotal + compressed);
		writer.close();

//		BitWriter writer = new BitWriter(filename+".huf");
//		BitSet bitSet = new BitSet(compressed.length());
//		int bitcounter = 0;
//
//		for (char c : encabezadoTotal.toCharArray()) {
//			writer.writeBit(c);
//		}
//
//		for(Character c : compressed.toCharArray()) {
//		    if(c.equals('1')) {
//		        bitSet.set(bitcounter);
//		    }
//		    bitcounter++;
//		}
//
//		writer.writeBytes(bitSet.toByteArray());
//		writer.close();
	}
	
	public static void descomprimir(String filename) throws IOException
	{
		BitReader reader = new BitReader(filename);
		Map<String, Character> codes = new HashMap<String, Character> ();
		Integer largo = Compression.getCodes(reader, codes);
		String decompressed = Decompression.decompressText(Decompression.extractCompressedText(filename), codes);
		System.out.println(decompressed);
		BitWriter writer = new BitWriter(filename.substring(0, filename.length()-4)+"_desc");
		for (char c : decompressed.toCharArray()) {
			writer.writeBit(c);
		}
	}
}
