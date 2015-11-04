package org.jointheleague.erik.level4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class AnagramsTest extends TestCase {

    Anagrams instance;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        List<String> words = Arrays.asList(
                "acts", "cast",
                "grounded", "underdog", "undergod",
                "post", "spot", "stop", "tops");
        InputStream wordSource = new ByteArrayInputStream(stringsToBytes(words));
        instance = new Anagrams(wordSource);
    }

    public byte[] stringsToBytes(List<String> words) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte newline = (byte) '\n';
        for (String w : words) {
            baos.write(w.getBytes());
            baos.write(newline);
        }
        return baos.toByteArray();
    }

    @Test
    public void testGetRep() {
        assertEquals("aelst", Anagrams.getRep("steal"));
        assertEquals("aailmn", Anagrams.getRep("animal"));
        assertEquals("ddegnoru", Anagrams.getRep("grounded"));
        assertEquals("beggir", Anagrams.getRep("bigger"));
        assertEquals("aellmrs", Anagrams.getRep("smaller"));
        assertEquals("beggir", Anagrams.getRep("bigger"));
        assertEquals("aelqu", Anagrams.getRep("equal"));
        assertEquals("ellsw", Anagrams.getRep("swell"));
    }

    @Test
    public void testIsAnagramPositive() {
        assertTrue(Anagrams.isAnagram2("stale", "tales"));
        assertTrue(Anagrams.isAnagram2("stale", "slate"));
        assertTrue(Anagrams.isAnagram2("aabbb", "babab"));
    }

    @Test
    public void testIsAnagramNegative() {
        assertFalse(Anagrams.isAnagram2("apple", "banana"));
        assertFalse(Anagrams.isAnagram2("stale", "tales "));
        assertFalse(Anagrams.isAnagram2("abbbb", "babab"));
        assertFalse(Anagrams.isAnagram2("aabbb", "babbb"));
        assertFalse(Anagrams.isAnagram2("loooooong", "shrt"));
    }

    @Test
    public void testIsAnagramCaseSensitivity() {
        assertTrue(Anagrams.isAnagram2("STALE", "Slate"));
        assertTrue(Anagrams.isAnagram2("BANANA", "baNanA"));
    }

    @Test
    public void testIsAnagramWithEmptyString() {
        assertFalse(Anagrams.isAnagram2("banana", ""));
        assertFalse(Anagrams.isAnagram2("", "banana"));
        assertTrue(Anagrams.isAnagram2("", ""));

    }

    @Test
    public void testIsAnagramWithNull() {
        assertFalse(Anagrams.isAnagram2("banana", null));
        assertFalse(Anagrams.isAnagram2(null, "banana"));
        assertFalse(Anagrams.isAnagram2(null, ""));
        assertFalse(Anagrams.isAnagram2("", null));
        assertFalse(Anagrams.isAnagram2(null, null));
    }

    @Test
    public void testFindAnagramsOfNonWord() {
        assertTrue(instance.findAnagrams("strmpf").isEmpty());
    }

    @Test
    public void testAnagramsOfSpot() {
        String anagrams = instance.findAnagrams("spot");
        assertTrue(anagrams.lastIndexOf("stop") != -1);
        assertTrue(anagrams.lastIndexOf("post") != -1);
        assertTrue(anagrams.lastIndexOf("tops") != -1);
        assertTrue(anagrams.lastIndexOf("spot") != -1);
    }

    @Test
    public void testAnagramsOfGrounded() {
        String anagrams = instance.findAnagrams("grounded");
        assertTrue(anagrams.lastIndexOf("underdog") != -1);
        assertTrue(anagrams.lastIndexOf("undergod") != -1);
    }

    @Test
    public void testAnagramsOfCats() {
        String anagrams = instance.findAnagrams("cats").toLowerCase();
        assertTrue(anagrams.lastIndexOf("acts") != -1);
        assertTrue(anagrams.lastIndexOf("cast") != -1);
        assertTrue(anagrams.lastIndexOf("scat") == -1);
        assertTrue(anagrams.lastIndexOf("cats") == -1);
    }
}
