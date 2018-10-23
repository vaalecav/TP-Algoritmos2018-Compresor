package jhuffman;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import jhuffman.util.Compression;

public class Huffman
{	
	public static void main(String[] args) throws IOException
	{
		String filename = args[0];
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
		Map<Character,String> huffmanTree = Compression.getMappedTree(filename);
		String header = Compression.getHuffmanHeader(huffmanTree);
		String compressed = Compression.getCompressedContent(huffmanTree, filename);
	
		
		//escribir en archivo los 3 concatenados
	}
	
	public static void descomprimir(String filename)
	{
		// TODO descomprimir(String filename)
	}
}
