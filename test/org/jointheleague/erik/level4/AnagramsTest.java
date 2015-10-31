package org.jointheleague.erik.level4;
import static org.junit.Assert.*;

import org.jointheleague.erik.level4.Anagrams;
import org.junit.Test;

public class AnagramsTest {

    @Test
    public void testIsAnagramPositive() {
        assertTrue(Anagrams.isAnagram("stale", "tales"));
        assertTrue(Anagrams.isAnagram("stale", "slate"));
        assertTrue(Anagrams.isAnagram("aabbb", "babab"));
    }
    
    @Test
    public void testIsAnagramNegative() {
        assertFalse(Anagrams.isAnagram("apple", "banana"));
        assertFalse(Anagrams.isAnagram("stale", "tales "));
        assertFalse(Anagrams.isAnagram("abbbb", "babab"));
        assertFalse(Anagrams.isAnagram("aabbb", "babbb"));
        assertFalse(Anagrams.isAnagram("loooooong", "shrt"));
    }
    @Test
    public void testIsAnagramCaseSensitivity() {
        assertTrue(Anagrams.isAnagram("STALE", "Slate"));
        assertTrue(Anagrams.isAnagram("BANANA", "baNanA"));
    }
    
    @Test
    public void testIsAnagramWithEmptyString() {
        assertFalse(Anagrams.isAnagram("banana", ""));
        assertFalse(Anagrams.isAnagram("", "banana"));
        assertTrue(Anagrams.isAnagram("", ""));
        
    }

    @Test
    public void testIsAnagramWithNull() {
        assertFalse(Anagrams.isAnagram("banana", null));
        assertFalse(Anagrams.isAnagram(null, "banana"));
        assertFalse(Anagrams.isAnagram(null, ""));
        assertFalse(Anagrams.isAnagram("", null));
        assertFalse(Anagrams.isAnagram(null, null));
    }

}
