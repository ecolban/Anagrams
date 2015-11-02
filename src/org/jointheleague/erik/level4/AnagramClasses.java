package org.jointheleague.erik.level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class AnagramClasses {

    public static void main(String[] args) throws IOException, URISyntaxException {
        InputStream is = AnagramClasses.class.getResourceAsStream("res/master.txt");
        Collection<List<String>> wordClasses;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            wordClasses = reader.lines().collect(Collectors.groupingBy(Anagrams::getRep)).values();
        }
        List<List<String>> wordClassList = new ArrayList<>(wordClasses);
        wordClassList.sort((c1, c2) -> c2.size() - c1.size());
        wordClassList.stream().limit(20).forEach(System.out::println);
    }
}