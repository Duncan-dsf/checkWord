package dsf.checkWord.dto;

import dsf.checkWord.entity.Word;
import dsf.checkWord.entity.WordParagraph;

import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 董少飞
 * @date 2018/10/14
 */
public abstract class AbstractWordMap implements WordMap {

    protected final ConcurrentHashMap<Integer, LinkedList<WordParagraph>> paragraphMap;

    protected volatile Boolean isMapReady = false;

    private void waitForMap() {

        while(!isMapReady) {

            try {
                isMapReady.wait();
            } catch (InterruptedException e) {

                System.out.println("wait 被打断");
                e.printStackTrace();
            }
        }

    }

    public AbstractWordMap() {

        paragraphMap = new ConcurrentHashMap<>();
    }

    @Override
    public LinkedList<WordParagraph> getParagraphsByTitleLevel(int titleLevel) {
        return paragraphMap.get(titleLevel);
    }

    @Override
    public boolean addParagraphAtTitleLevel(int titleLevel, WordParagraph wordParagraph) {

        LinkedList<WordParagraph> wordParagraphs = paragraphMap.computeIfAbsent(titleLevel, k -> new LinkedList<WordParagraph>());
        synchronized (wordParagraphs) {

            return wordParagraphs.add(wordParagraph);
        }
    }

    @Override
    public boolean removeParagraphAtTitleLevel(int titleLevel, WordParagraph wordParagraph) {

        LinkedList<WordParagraph> wordParagraphs = paragraphMap.get(titleLevel);

        synchronized (wordParagraphs) {


            return wordParagraphs.remove(wordParagraph);
        }
    }

    @Override
    public LinkedList<WordParagraph> getWordParagraphs(int titleLevel) {

        waitForMap();
        LinkedList<WordParagraph> wordParagraphs = paragraphMap.get(titleLevel);
        return (LinkedList<WordParagraph>) wordParagraphs.clone();
    }
}
