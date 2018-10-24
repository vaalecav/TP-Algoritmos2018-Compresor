package jhuffman.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map.Entry;

import jhuffman.ds.Node;

public class BitReader {
	private Reader reader;
	private Reader buffer;
	private String bitBuffer="";
	
	public BitReader(String filename) {
		File file = new File(filename);
		try {
			InputStream in = new FileInputStream(file);
			this.reader = new InputStreamReader(in, Charset.defaultCharset());
			this.buffer = new BufferedReader(reader);
		} catch (IOException e) {
			System.err.println("File " + filename + " cannot be read: " + e.getMessage());
			return;
		}
	}

	public SortedList<Node> mapCharToFreq() {
		FrequencyMapper fm = new FrequencyMapper();
		SortedList<Node> frequencies = new SortedList<Node>();
		int bit;
		while (!eof(bit = readBit())) {
			fm.logAppearance((char) bit);
		}
		for (Entry<Character, Integer> cf : fm.getList().entrySet()) {
			frequencies.add(new Node(cf.getKey(), cf.getValue(), null, null), new ComparatorCharFreq());
		}

		return frequencies;
	}

	public int readBit() {
		try {
			return reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	public int trueReadBit() {
		if(bitBuffer.length() < 1){
			bitBuffer = BinaryConverter.stringTobinary(Integer.toString(readBit()));
		}
		int c = bitBuffer.charAt(0) == '1' ? 1 : 0;
		bitBuffer = bitBuffer.substring(1);
		return c;
	}

	public boolean eof(int r) {
		if (r != -1) {
			return false;
		}
		return true;
	}

	public void close() {
		// TODO close()
	}
}
