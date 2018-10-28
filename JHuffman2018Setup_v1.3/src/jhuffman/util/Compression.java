package jhuffman.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jhuffman.ds.Node;
import jhuffman.util.files.BinaryConverter;

public class Compression {

	public static Map<Character, String> getHuffmanForFile(String filename) {
		BitReader bitReader = new BitReader(filename);
		SortedList<Node> list = bitReader.mapCharToFreq();
		Node root = TreeUtil.makeTree(list);

		StringBuffer sb = new StringBuffer();
		TreeUtil ut = new TreeUtil(root);
		Map<Character, String> huffmanPerCharacter = new HashMap<Character, String>();

		// primera hoja
		Node x = ut.next(sb);

		while (x != null) {
			// armo hash map
			huffmanPerCharacter.put((char) x.getC(), sb.toString());
			// siguiente hoja
			x = ut.next(sb);
		}
		return huffmanPerCharacter;
	}

	// Devuelve el arbol huffman
	public static String getHuffmanHeader(Map<Character, String> huffmanTree) {
		StringBuilder header = new StringBuilder();

		for (Character c : huffmanTree.keySet()) {
			header.append(c);
			header.append(huffmanTree.get(c));
			header.append('|');
		}
		return header.toString();

	}


	public static String compressText(Map<Character, String> huffmanPerCharacter, String text) {
		StringBuilder compressedText = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			String huffed = huffmanPerCharacter.get(text.charAt(i));
			compressedText.append(huffed);
		}
		return compressedText.toString();
	}

}
