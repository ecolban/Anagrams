package org.jointheleague.erik.level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Anagrams {

    private Map<String, List<String>> map;

    public Anagrams(InputStream wordSource) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(wordSource))) {
            map = reader.lines().collect(Collectors.groupingBy(Anagrams::getRep));
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Anagrams anagrams = new Anagrams(Anagrams.class.getResourceAsStream("res/words.txt"));
        List<List<String>> classes = new ArrayList<>(anagrams.map.values());
        classes.sort((c1, c2) -> c2.size() - c1.size());
        classes.stream().limit(20).forEach(System.out::println);
        anagrams.run();
    }

    private void run() throws IOException, URISyntaxException {

        for (boolean play = true; play;) {
            String w = JOptionPane.showInputDialog("Which word?");
            String msg = (w == null ? "No anagrams." : "Anagrams: " + findAnagrams(w))
                    + "\nTry again?";
            int answer = JOptionPane.showConfirmDialog(null, msg, "Anagrams",
                    JOptionPane.YES_NO_OPTION);
            play = answer == JOptionPane.YES_OPTION;
        }
    }

    public static String getRep(String s) {
        char[] b = s.toLowerCase().toCharArray();
        Arrays.sort(b);
        return new String(b);
    }

    public String findAnagrams(String word) {
        String key = getRep(word);
        if (map.containsKey(key)) {
            return map.get(key).stream().collect(Collectors.joining(", "));
        } else {
            return "";
        }
    }

    /* Three different implementations of the isAnagram() method */

    public static boolean isAnagram1(String word1, String word2) {
        if (word1 != null && word2 != null && word1.length() == word2.length()) {
            char[] letters1 = word1.toLowerCase().toCharArray();
            char[] letters2 = word2.toLowerCase().toCharArray();
            Arrays.sort(letters1);
            Arrays.sort(letters2);
            return Arrays.equals(letters1, letters2);
        } else {
            return false;
        }
    }

    public static boolean isAnagram2(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        } else {
            return Anagrams.getRep(word1).equals(Anagrams.getRep(word2));
        }
    }

    public static boolean isAnagram3(String word1, String word2) {
        if (word1 != null && word2 != null
                && word1.length() == word2.length()) {
            char[] letters1 = word1.toLowerCase().toCharArray();
            char[] letters2 = word2.toLowerCase().toCharArray();
            boolean matched = true;
            for (int i = 0; matched && i < letters1.length; i++) {
                matched = false;
                for (int j = 0; !matched && j < letters2.length; j++) {
                    if (letters1[i] == letters2[j]) {
                        letters2[j] = 'X';
                        matched = true;
                    }
                }
            }
            return matched;
        } else {
            return false;
        }
    }

}
