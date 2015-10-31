package org.jointheleague.erik.level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Anagrams {

    public static void main(String[] args)
            throws IOException, URISyntaxException {
        new Anagrams().run();
    }

    static public boolean isAnagram1(String word1, String word2) {
        if (word1 != null && word2 != null
                && word1.length() == word2.length()) {
            char[] letters1 = word1.toLowerCase().toCharArray();
            char[] letters2 = word2.toLowerCase().toCharArray();
            Arrays.sort(letters1);
            Arrays.sort(letters2);
            return Arrays.equals(letters1, letters2);
        } else {
            return false;
        }
    }

    static public boolean isAnagram(String word1, String word2) {
        if (word1 != null && word2 != null
                && word1.length() == word2.length()) {
            char[] letters1 = word1.toLowerCase().toCharArray();
            char[] letters2 = word2.toLowerCase().toCharArray();
            boolean matched = true;
            for (int i = 0; matched && i < letters1.length; i++) {
                matched = false;
                for(int j = 0; !matched && j < letters2.length; j++) {
                    if(letters1[i] == letters2[j]) {
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

    private void run() throws IOException, URISyntaxException {
        URLConnection connection = new URL(
                "https://raw.githubusercontent.com/eneko/data-repository/master/data/words.txt")
                        .openConnection();
        List<String> words;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            words = reader.lines().collect(Collectors.toList());
        }
        for (boolean play = true; play;) {
            String w = JOptionPane.showInputDialog("Which word?");
            String msg = w == null
                    ? "No anagrams."
                    : "Anagrams: " + findAnagrams(w, words) + "\nTry again?";
            int answer = JOptionPane.showConfirmDialog(null, msg, "Anagrams",
                    JOptionPane.YES_NO_OPTION);
            play = answer == JOptionPane.YES_OPTION;
        }
    }

    private String findAnagrams(String word, List<String> words)
            throws IOException {
        return words.stream()
                .filter(w -> Anagrams.isAnagram(word, w))
                .collect(Collectors.joining(", "));
    }

}
