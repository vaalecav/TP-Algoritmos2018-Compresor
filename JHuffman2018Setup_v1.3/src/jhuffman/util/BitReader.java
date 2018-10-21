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

import jhuffman.ds.CharFreq;

public class BitReader {
	private Reader reader;
	private Reader buffer;

	// private RandomAccessFile raf = null;
	public SortedList<CharFreq> mapCharToFreq() {
		FrequencyMapper fm = new FrequencyMapper();
		SortedList<CharFreq> frequencies = new SortedList<CharFreq>();
		int bit;
		while (!eof(bit = readBit())) {
			fm.logAppearance((char) bit);
		}
		for (Entry<Character, Integer> cf : fm.getList().entrySet()) {
			frequencies.add(new CharFreq(cf.getKey(), cf.getValue()), new ComparatorCharFreq());
		}

		return frequencies;
	}

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

	public int readBit() {
		try {
			return reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

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
