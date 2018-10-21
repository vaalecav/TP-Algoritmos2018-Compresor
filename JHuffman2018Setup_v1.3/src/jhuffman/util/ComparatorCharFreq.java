package jhuffman.util;

import java.util.Comparator;

import jhuffman.ds.CharFreq;

public class ComparatorCharFreq implements Comparator<CharFreq>{

	@Override
	public int compare(CharFreq o1, CharFreq o2) {
		return o1.getFrequency()-o2.getFrequency();
	}

}
