package dsf.checkWord.dto;

import dsf.checkWord.entity.WordParagraph;
import dsf.checkWord.entity.WordRun;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.*;

public class WordDifference {

    private final WordParagraph rule;

    private final List<WordParagraph> paragraphs = Collections.synchronizedList(new LinkedList<>());

    public WordDifference(WordParagraph rule) {

        this.rule = rule;
    }

    public boolean addDifferenceParagraph(WordParagraph wordParagraph) {

        return paragraphs.add(wordParagraph);
    }

    public static void showMap(Map<WordParagraph, Map<WordRun, String>> map, PrintStream printStream) throws IOException {

        for(Map.Entry<WordParagraph, Map<WordRun, String>> entry : map.entrySet()) {

            WordParagraph wordParagraph = entry.getKey();
            if(wordParagraph.isEmpty()) {
                continue;
            }
            String text = wordParagraph.text();
            int length = text.length()>10?10:text.length();
            printStream.println(text.substring(0, length) + "...段落");
            Map<WordRun, String> runStringMap = entry.getValue();
            for(Map.Entry<WordRun, String> runStringEntry : runStringMap.entrySet()) {

                WordRun wordRun = runStringEntry.getKey();
                if(wordRun != null && !wordRun.isEmpty()) {

                    String value = runStringEntry.getValue();
                    if(value != null && !value.trim().equals("")) {

                        printStream.println("       " + wordRun.text() + "  处");
                        printStream.println("       " + value);
                    }
                }
            }


        }
        printStream.close();
    }
}
