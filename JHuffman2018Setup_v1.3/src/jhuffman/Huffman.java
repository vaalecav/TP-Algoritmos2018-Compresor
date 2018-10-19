package jhuffman;

public class Huffman
{	
	public static void main(String[] args)
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

	public static void comprimir(String filename)
	{
		// PROGRAMAR AQUI...
	}
	
	public static void descomprimir(String filename)
	{
		// PROGRAMAR AQUI...
	}
}
