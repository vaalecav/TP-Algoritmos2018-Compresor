package jhuffman.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class BitReader
{
	//private RandomAccessFile raf = null;
	Map<Character, Integer> frequencies = new HashMap<Character, Integer>();
	
	public BitReader(String filename)
	{
		File file = new File(filename);
		try (InputStream in = new FileInputStream(file);
				Reader reader = new InputStreamReader(in,Charset.defaultCharset());
				Reader buffer = new BufferedReader(reader)) {
			readBit(reader);
		} catch (IOException e) {
			System.err.println("File " + filename + " cannot be read: " + e.getMessage());
			return;
		}
	}
	
	public int readBit(Reader reader) throws IOException
	{
		int read;
		while (eof(read = reader.read())) {
			Character c = new Character((char) read);
			Integer freq = frequencies.get(c);
			if (freq != null) {
				frequencies.put(c, new Integer(freq + 1));
			} else {
				frequencies.put(c, new Integer(1));
			}
		}
		return 0;
	}
	
	public boolean eof(int r)
	{
		if (r != -1){
			return false;
		}
		return true;
	}
		
	public void close()
	{
		// programar aqui		
	}
}
