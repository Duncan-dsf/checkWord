package dsf.checkWord.dto;

import dsf.checkWord.entity.WordParagraph;

import java.util.*;

/**
 * 在不考虑word的结构的情况下——只考虑样式
 * 解析word之后 生成WordMap对象
 * 可以获得所有不同标题级别的段落
 *
 * 也许用lst更贴切一点 但考虑到需要并发的安全性，
 * 而list的线程安全容器只有 {@link java.util.concurrent.CopyOnWriteArrayList} 和 {@link java.util.Collections.SynchronizedList}
 * 两者并发性能都比较差
 * 所以决定用 {@link java.util.concurrent.ConcurrentHashMap}
 *
 * @author 董少飞
 * @date 2018/10/13
 */
public interface WordMap {

    /**
     * 根据titleLevel 获取对应paragraph list
     * @param titleLevel 标题级别
     * @return 指定标题级别对应的paragraph集合 链表 按段落顺序构成
     */
    LinkedList<WordParagraph> getParagraphsByTitleLevel(int titleLevel);

    /**
     * 往对应标题级别的段落集合中添加一个元素
     * @param titleLevel
     * @return
     */
    boolean addParagraphAtTitleLevel(int titleLevel, WordParagraph wordParagraph);

    /**
     * 往对应标题级别的段落集合中移除一个元素
     * @param titleLevel
     * @return
     */
    boolean removeParagraphAtTitleLevel(int titleLevel, WordParagraph wordParagraph);

    /**
     * 获取对应标题级别的段落列表
     * @param titleLevel
     * @return
     */
    LinkedList<WordParagraph> getWordParagraphs(int titleLevel);
}
