package jhuffman.util;

import java.util.Comparator;

import jhuffman.ds.Node;

public class ComparatorCharFreq implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		int dif = (int) (o2.getN()-o1.getN());
		return (int) (dif ==0?o2.getC()-o1.getC():dif);
	}

}
