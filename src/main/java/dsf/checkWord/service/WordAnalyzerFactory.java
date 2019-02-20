package dsf.checkWord.service;

import dsf.checkWord.entity.Word;
import dsf.checkWord.entity.WordFactory;

public class WordAnalyzerFactory {

    public static WordAnalyzer getWordAnalyzer(String file) {

        Word word = WordFactory.getWord(file);
        return new WordAnalyzerImpl(word);
    }
}
