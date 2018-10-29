package jhuffman.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;

import jhuffman.ds.Node;
import jhuffman.util.files.BinaryConverter;
import jhuffman.util.files.InputFile;

public class BitReader {
	private InputFile reader;
	
	public BitReader(String filename) {
		try {
			reader = new InputFile(filename);
		} catch (IOException e) {
			System.err.println("File " + filename + " cannot be read: " + e.getMessage());
			return;
		}
	}

	public SortedList<Node> mapCharToFreq() {
		FrequencyMapper fm = new FrequencyMapper();
		SortedList<Node> frequencies = new SortedList<Node>();
		Character bit;
		while (!eof(bit = readBit())) {
			fm.logAppearance(bit);
		}
		for (Entry<Character, Integer> cf : fm.getList().entrySet()) {
			frequencies.add(new Node(cf.getKey(), cf.getValue(), null, null), new ComparatorCharFreq());
		}

		return frequencies;
	}

	public Character readBit() {
		return reader.getNextChar();
	}


	public boolean eof(Character r) {
		if (r != null) {
			return reader.eof();
		}
		return true;
	}

	public void close() {
		reader.close();
	}
}
