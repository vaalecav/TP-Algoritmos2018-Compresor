package jhuffman.util.files;

import java.nio.ByteBuffer;

public class ByteUtils {
	private static ByteBuffer buffer;

	public static byte[] longToBytes(long x) {
		buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(0, x);
		return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
		buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();//need flip
		return buffer.getLong();
	}
}