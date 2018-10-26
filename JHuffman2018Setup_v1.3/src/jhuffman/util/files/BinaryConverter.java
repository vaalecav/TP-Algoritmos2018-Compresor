package jhuffman.util.files;

import java.nio.charset.StandardCharsets;

public class BinaryConverter {

	//Para un string de
	public static char[] binaryToString(String s){

		while(s.length()%8 != 0){
			s += "0";
		}

		char[] str = new char[0];
		for (int i = 0; i < s.length()/8; i++) {

			int a = Integer.parseInt(s.substring(8*i,(i+1)*8),2);
			char[] aux = new char[1];
			aux[0] = (char)a;
			str = concatCharArrays(str, aux);
		}

		return str;
	}

	public static String stringTobinary(String text){
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < text.length(); i++){
			String aux = Integer.toBinaryString( text.charAt(i));
			while(aux.length() % 8 != 0) aux = "0" + aux;
			output.append(aux);
		}
		return output.toString();
	}

	private static char[] concatCharArrays(char[] a, char[] b){
		char[] c = new char[a.length + b.length];
		int i = 0;
		while(i < a.length){
			c[i] = a[i];
			i++;
		}
		while(i-a.length < b.length){
			c[i] = b[i-a.length];
			i++;
		}
		return c;
	}
}
