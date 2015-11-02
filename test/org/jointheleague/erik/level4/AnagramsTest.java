package org.jointheleague.erik.level4;
import static org.junit.Assert.*;

import org.jointheleague.erik.level4.Anagrams;
import org.junit.Test;

public class AnagramsTest {

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

}
