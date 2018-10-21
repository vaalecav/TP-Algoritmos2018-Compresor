package jhuffman.util;

import java.util.HashMap;
import java.util.Map;


public class FrequencyMapper {
	Map<Character,Integer> frequencies = new HashMap<Character,Integer>();

	void logAppearance(Character c) {
		Integer freq = frequencies.get(c);
		if (freq != null) {
			frequencies.put(c, new Integer(freq + 1));
		} else {
			frequencies.put(c, new Integer(1));
		}
	}

	public Map<Character,Integer> getList() {
		return frequencies;
	}

	
}
