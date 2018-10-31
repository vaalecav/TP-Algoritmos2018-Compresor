package jhuffman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class Decompression {
	
	public static String extractCompressedText(String fileName, Integer largo) {
		String datos = "";
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader =
					new BufferedReader(fileReader);
			Integer offsetPorCabecera = bufferedReader.read();
			while (offsetPorCabecera-- > 0) {
				bufferedReader.read();
			}
			datos = bufferedReader.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		return datos;
	}

}
