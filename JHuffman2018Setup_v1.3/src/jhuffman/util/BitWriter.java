package jhuffman.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BitWriter
{
	private RandomAccessFile raf = null;
	
	public BitWriter(String filename) throws FileNotFoundException
	{
		raf = new RandomAccessFile(filename, "rw");
	}
		
	public void writeBit(int bit) throws IOException
	{
		raf.write(bit);
	}
	
	public void flush()
	{
		//TODO  flush()
	}
	
	public void close() throws IOException
	{
		raf.close();
	}
}
