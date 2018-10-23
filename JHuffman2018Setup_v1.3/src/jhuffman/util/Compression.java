package jhuffman.util;

import java.util.HashMap;
import java.util.Map;

import jhuffman.ds.Node;

public class Compression {
	
	public static void encoder(SortedList<Node> list){
		Node root = TreeUtil.makeTree(list);
		
		StringBuffer sb = new StringBuffer();
		TreeUtil ut = new TreeUtil(root);
		Map<Character,String> huffmanPerCharacter = new HashMap<Character,String>();
		
		// primera hoja
		Node x = ut.next(sb);
		
		while (x != null) {
		// armo hash map
			huffmanPerCharacter.put((char) x.getC(), sb.toString());
			// siguiente hoja
			x = ut.next(sb);		
		}
	}
	
	public static void getCompressedFile(){
		
	}
}
