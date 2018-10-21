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
		// TODO comprimir(String filename)
	}
	
	public static void descomprimir(String filename)
	{
		// TODO descomprimir(String filename)
	}
}
