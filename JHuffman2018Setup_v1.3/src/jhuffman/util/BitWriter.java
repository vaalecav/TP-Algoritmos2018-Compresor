package jhuffman.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BitWriter
{
	private RandomAccessFile raf = null;
	FileOutputStream fos;
	
	public BitWriter(String filename) throws FileNotFoundException
	{
		raf = new RandomAccessFile(filename, "rw");
		fos = new FileOutputStream(filename, true);
	}
		
	public void writeBit(int bit) throws IOException 
	{
		raf.write(bit);
	}
	
	public void writeBytes(byte[] bytes) throws IOException{
		fos.write(bytes);
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
