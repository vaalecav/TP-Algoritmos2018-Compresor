package jhuffman.util;

import jhuffman.util.files.BinaryConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class Decompression {
	
	public static String extractCompressedText(String fileName) {
		String datos = "";
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader =
					new BufferedReader(fileReader);

			bufferedReader.readLine();//Mapa
			Integer largo = Integer.parseInt(bufferedReader.readLine());//largo
			datos = bufferedReader.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		return datos;
	}

	public static String decompressText(String text, Map<String, Character> huffmanPerBinSet, Integer largo){
		String bin = BinaryConverter.stringTobinary(text);

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
}
