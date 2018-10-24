package test;

import jhuffman.util.Compression;
import jhuffman.util.Decompression;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {

    @Test
    public void compress(){
        String text = "abc";
        Map<Character, String> huffmanPerCharacter = new HashMap<>();
        huffmanPerCharacter.put('a', "01");
        huffmanPerCharacter.put('b', "00");
        huffmanPerCharacter.put('c', "1111");
        String compressed = Compression.compressText(huffmanPerCharacter, text);
        assertEquals("O", compressed);
    }

    @Test
    public void decompress(){
        String text = "O";
        Map<String, Character> huffmanPerCharacter = new HashMap<>();
        huffmanPerCharacter.put("01", 'a');
        huffmanPerCharacter.put("00", 'b');
        huffmanPerCharacter.put("1111", 'c');
        String decompressed = Decompression.decompressText(text, huffmanPerCharacter);
        assertEquals("abc", decompressed);
    }
}
