package jhuffman.util.files;

public class BinaryConverter {

	//Para un string de
	public static String binaryToString(String s){

		while(s.length()%8 != 0){
			s += "0";
		}

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < s.length()/8; i++) {

			int a = Integer.parseInt(s.substring(8*i,(i+1)*8),2);
			str.append( (char)(a) );
		}

		return str.toString();
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
}
