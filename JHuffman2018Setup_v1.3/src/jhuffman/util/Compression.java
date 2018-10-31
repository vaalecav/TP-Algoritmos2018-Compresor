package jhuffman.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jhuffman.ds.Node;

public class Compression {

	public static int  getMappedTree(String filename, Map<Character, String> huffmanPerCharacter) {
		BitReader bitReader = new BitReader(filename);
		SortedList<Node> list = new SortedList<Node>();
		int longitudDelTextoAComprimir = bitReader.mapCharToFreq(list);
		Node root = TreeUtil.makeTree(list);

		StringBuffer sb = new StringBuffer();
		TreeUtil ut = new TreeUtil(root);

		// primera hoja
		Node x = ut.next(sb);

		while (x != null) {
			// armo hash map
			huffmanPerCharacter.put((char) x.getC(), sb.toString());
			// siguiente hoja
			x = ut.next(sb);
		}
		return longitudDelTextoAComprimir;
	}

	// Devuelve el arbol huffman
	public static String getHuffmanHeader(Map<Character, String> huffmanTree) {
		String header = "";

		for (Character c : huffmanTree.keySet()) {
			header += c + String.valueOf(huffmanTree.get(c).length()) + huffmanTree.get(c);
		}
		return header;

	}

	// Devuelve longitud del archivo y el archivo comprimido
	public static String getCompressedContent(Map<Character, String> huffmanTree, String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		String compressedContent = "";
		int length = 0, i;

		while ((i = fr.read()) != -1) {
			length++;
			compressedContent += huffmanTree.get((char) i);
		}
		fr.close();

		return ((char) length) + (compressedContent);
	}

	public static String compressText(String text, Map<Character, String> huffmanPerCharacter) {
		StringBuilder compressedText = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			String huffed = huffmanPerCharacter.get(text.charAt(i));
			compressedText.append(huffed);
		}
		String builded = compressedText.toString();
		return BinaryConverter.binaryToString(builded);
	}

	public static Integer getCodes(BitReader reader, Map<String, Character> output) {
		int c;
		 
		int tamanioCabecera = reader.readBit();
		while (tamanioCabecera > 0) {
			c = reader.readBit();
			tamanioCabecera -- ;
			int length = reader.readBit() - '0';
			tamanioCabecera -- ;
			String code = "";
			for (int i = 0; i < length; i++) {
				code += reader.readBit() - '0';
				tamanioCabecera -- ;
			}
			output.put(code, (char) c);
		}
		int largo =reader.readBit();
		return largo;
	}

	public static String getDecompressedContent(Map<String, Character> codes, BitReader reader, Integer largo) {
		String output = "";
		int c;
		
		String code = "";
		reader.readBit();// el primero es la longitud
			while (output.length() < largo && !reader.eof(c = reader.readBit() - '0')) {
				code += c;
				Character caracter = codes.get(code);
				if (caracter != null) {
					output += caracter;
					code = "";
					continue;
				}
		}
		return output;
	}
}
