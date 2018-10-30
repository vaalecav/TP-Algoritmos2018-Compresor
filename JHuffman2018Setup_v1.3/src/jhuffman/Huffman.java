package jhuffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import jhuffman.util.BitReader;
import jhuffman.util.Compression;
import jhuffman.util.Decompression;

public class Huffman
{	
	public static void main(String[] args) throws IOException
	{
		String filename = "test.bmp.huf";//args[0];
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

		/*FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String decompressed = "", aux = "";
		while((aux = bufferedReader.readLine()) != null){
			decompressed += aux;
		}*/
		
		String encabezadoTotal = header + "\n" + file.length() + "\n"; //le concateno la longitud del archivo
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".huf"));
		writer.write(encabezadoTotal);
		writer.close();
		
		// escribir 1 a 1 lo que va comprimiendo
		//String compressed = Compression.compressText(huffmanTree, decompressed);
		SCompression.compressContent(huffmanTree, filename);

		
	}
	
	public static void descomprimir(String filename) throws IOException
	{
		BitReader reader = new BitReader(filename);
		Map<String, Character> codes = new HashMap<String, Character> ();
		Integer largo = Compression.getCodes(reader, codes);
		String decompressed = Decompression.decompressText(Decompression.extractCompressedText(filename), codes, largo);
		BufferedWriter writer = new BufferedWriter(new FileWriter("desc_" + filename.substring(0, filename.length()-4)));
		writer.write(decompressed);
		writer.close();
	}
}
