package jhuffman.util;

import java.util.Map;

public class Decompression {
	
	public void decode(String text) {

	}

	public static String decompressText(String text, Map<String, Character> huffmanPerBinSet){
		String bin = BinaryConverter.stringTobinary(text);

		StringBuilder decompressedText = new StringBuilder();

		for(int i = 0; i <= bin.length(); i++){

			String subBin = bin.substring(0, i);

			if( huffmanPerBinSet.containsKey( subBin ) ){

				decompressedText.append( huffmanPerBinSet.get(subBin) );
				bin = bin.substring(i);
				i = 0;
			}
		}

		return decompressedText.toString();
	}
}
