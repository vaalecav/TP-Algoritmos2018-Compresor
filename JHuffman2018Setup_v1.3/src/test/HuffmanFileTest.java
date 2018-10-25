package test;

import jhuffman.util.files.FileMode;
import jhuffman.util.files.FileModeMismatchException;
import jhuffman.util.files.HuffmanFile;
import jhuffman.util.files.MissingDataException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HuffmanFileTest {

	@Test
	public void dumpAndRetrieveFile() throws FileModeMismatchException, IOException, MissingDataException {
		String cabecera = "abc";
		String bits = "00101010";
		Long largoOriginal = 3L;
		HuffmanFile out = new HuffmanFile("test.huf", FileMode.OUT);
		out.setHeader(cabecera);
		out.setCompressedBits(bits);
		out.setOriginalContentLenght(largoOriginal);
		out.dump();

		HuffmanFile in = new HuffmanFile("test.huf", FileMode.IN);
		in.retrieve();
		assertEquals(cabecera, in.getHeader());
		assertEquals(bits, in.getCompressedBits());
		assertEquals(largoOriginal, in.getOriginalContentLenght());
	}
}
