package test;

import jhuffman.util.BinaryConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryConverterTest {

	@Test
	public void binaryToText(){
		String bin = "0110100001100101011011000110110001101111";
		String converted = BinaryConverter.binaryToString(bin);
		assertEquals("hello", converted);
	}

	@Test
	public void textToBynary(){
		String text = "hello";
		String converted = BinaryConverter.stringTobinary(text);
		assertEquals("0110100001100101011011000110110001101111", converted );
	}
}
