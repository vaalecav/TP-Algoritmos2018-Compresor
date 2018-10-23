package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import jhuffman.ds.Node;
import jhuffman.util.BitReader;
import jhuffman.util.SortedList;
import jhuffman.util.TreeUtil;

public class Compressiontest {
	@Test
	public void getFrecuencies() {
		BitReader bitReader = new BitReader("cocorito.txt");
		SortedList<Node> freqs = bitReader.mapCharToFreq();
		assertNotNull(freqs);
		assert (freqs.size() > 0);
		for (Node cf : freqs) {
			if (cf.getC() == 'O')
				assertEquals(11, cf.getN());
			if (cf.getC() == 'C')
				assertEquals(7, cf.getN());
			if (cf.getC() == ' ')
				assertEquals(5, cf.getN());
			if (cf.getC() == 'M')
				assertEquals(5, cf.getN());
			if (cf.getC() == 'A')
				assertEquals(2, cf.getN());
			if (cf.getC() == 'E')
				assertEquals(2, cf.getN());
			if (cf.getC() == 'T')
				assertEquals(2, cf.getN());
			if (cf.getC() == 'I')
				assertEquals(1, cf.getN());
			if (cf.getC() == 'N')
				assertEquals(1, cf.getN());
			if (cf.getC() == 'R')
				assertEquals(1, cf.getN());
			if (cf.getC() == 'S')
				assertEquals(1, cf.getN());
			if (cf.getC() == 'U')
				assertEquals(1, cf.getN());
		}
	}

	@Test
	public void getTree() {
		BitReader bitReader = new BitReader("cocorito.txt");
		SortedList<Node> list = bitReader.mapCharToFreq();
		Node root = TreeUtil.makeTree(list);

		StringBuffer sb = new StringBuffer();
		TreeUtil ut = new TreeUtil(root);

		// primera hoja
		Node x = ut.next(sb);
		while (x != null) {
			// muestro el codigo Huffman
			System.out.println((char) x.getC() + ": " + sb);
			switch ((char) x.getC()) {
			case 'C':
				assertEquals(3, sb.toString().length());
				break;
			case 'M':
				assertEquals(3, sb.toString().length());
				break;
			case 'O':
				assertEquals(2, sb.toString().length());
				break;
			case ' ':
				assertEquals(3, sb.toString().length());
				break;
			case 'S':
				assertEquals(5, sb.toString().length());
				break;
			case 'R':
				assertEquals(5, sb.toString().length());
				break;
			case 'N':
				assertEquals(5, sb.toString().length());
				break;
			case 'I':
				assertEquals(5, sb.toString().length());
				break;
			case 'T':
				assertEquals(4, sb.toString().length());
				break;
			case 'E':
				assertEquals(4, sb.toString().length());
				break;
			case 'A':
				assertEquals(4, sb.toString().length());
				break;
			case 'U':
				assertEquals(4, sb.toString().length());
				break;
			}
			// siguiente hoja
			x = ut.next(sb);
			// exceptions.stream().forEachOrdered(e -> e.printStackTrace());
		}

	}

}
