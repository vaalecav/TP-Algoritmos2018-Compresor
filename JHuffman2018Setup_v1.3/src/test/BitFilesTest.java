package test;

import jhuffman.util.files.InputFile;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BitFilesTest {
	@Test
	public void readString() throws IOException {
		InputFile file = new InputFile("cocorito.txt");
		assertEquals(file.getAllChars(), "COMO COME COCORITO COME COMO COSMONAUTA");
	}
}
