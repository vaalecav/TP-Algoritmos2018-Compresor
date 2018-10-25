package test;

import jhuffman.util.InputFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

public class BitFilesTest {
	@Test
	public void readString() throws IOException {
		InputFile file = new InputFile("cocorito.txt");
		assertEquals(file.getAllChars(), "COMO COME COCORITO COME COMO COSMONAUTA");
	}
}
