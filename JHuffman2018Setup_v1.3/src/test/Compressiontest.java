package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import jhuffman.ds.CharFreq;
import jhuffman.ds.Node;
import jhuffman.util.BitReader;
import jhuffman.util.SortedList;

public class Compressiontest {
	@Test
	public void getFrecuencies() {
		BitReader bitReader = new BitReader("cocorito.txt");
		SortedList<CharFreq> freqs = bitReader.mapCharToFreq();
		assertNotNull(freqs);
		assert (freqs.size() > 0);
		for (CharFreq cf : freqs) {
			if (cf.getCharacter()=='_') assertEquals(5, cf.getFrequency().intValue());
			if (cf.getCharacter()=='a') assertEquals(2, cf.getFrequency().intValue());
			if (cf.getCharacter()=='c') assertEquals(7, cf.getFrequency().intValue());
			if (cf.getCharacter()=='e') assertEquals(2, cf.getFrequency().intValue());
			if (cf.getCharacter()=='I') assertEquals(1, cf.getFrequency().intValue());
			if (cf.getCharacter()=='m') assertEquals(5, cf.getFrequency().intValue());
			if (cf.getCharacter()=='n') assertEquals(1, cf.getFrequency().intValue());
			if (cf.getCharacter()=='o') assertEquals(11, cf.getFrequency().intValue());
			if (cf.getCharacter()=='r') assertEquals(1, cf.getFrequency().intValue());
			if (cf.getCharacter()=='s') assertEquals(1, cf.getFrequency().intValue());
			if (cf.getCharacter()=='t') assertEquals(2, cf.getFrequency().intValue());
			if (cf.getCharacter()=='u') assertEquals(1, cf.getFrequency().intValue());
		}
	}
	
	@Test
	public void getTree() {
		BitReader bitReader = new BitReader("cocorito.txt");
		SortedList<CharFreq> freqs = bitReader.mapCharToFreq();
		Node root = crearArbolHuffman(freqs);
	}

	private Node crearArbolHuffman(SortedList<CharFreq> freqs) {
		for (CharFreq cf : freqs) {
			Node n = new Node(cf.getCharacter(), cf.getFrequency(), null, null);
		}
		return null;
	}
	
}
