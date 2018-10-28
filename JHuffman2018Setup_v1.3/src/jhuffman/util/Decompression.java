package jhuffman.util;

import jhuffman.util.files.BinaryConverter;
import jhuffman.util.files.HuffmanFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Decompression {

	public static String decompressText(String bin, Map<String, Character> huffmanPerBinSet, Integer largo){

		StringBuilder decompressedText = new StringBuilder();

		for(int i = 0; i <= bin.length() && decompressedText.length() < largo; i++){

			String subBin = bin.substring(0, i);

			if( huffmanPerBinSet.containsKey( subBin ) ){

				decompressedText.append( huffmanPerBinSet.get(subBin) );
				bin = bin.substring(i);
				i = 0;
			}
		}

		return decompressedText.toString();
	}

	public static Map<String, Character> getHuffmanFromHeader(String header) {
		Map<String, Character> huffmanMap = new HashMap<>();
		Character c = null;
		StringBuilder code = new StringBuilder();
		for(int i =0; i < header.length(); i++){
			if(c == null){
				c = header.charAt(i);
			}else if(header.charAt(i) != '|'){
				code.append(header.charAt(i));
			}else{
				huffmanMap.put(code.toString(), c);
				code = new StringBuilder();
				c = null;
			}
		}
		return huffmanMap;
	}
}
