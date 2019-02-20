package dsf.checkWord.entity;

import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.Picture;

import java.io.File;
import java.util.List;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public interface Word {

    List<Picture> getImages();

    /**
     * 获取文件名
     * @return
     */
    String getName();

    void test();

    /**
     * 段落数
     * @return
     */
    int getNumParagraphs();

    /**
     * 获取指定index处的段落
     * @param index
     * @return
     */
    WordParagraph getParagraph(int index);

    /**
     * 获得样式表
     * @return
     */
    StyleSheet getStyleSheet();



    /**
     * 展示
     */
    void show();

    /**
     * 获得文本
     * @return
     */
    String text();

    /**
     * 写出到文件
     * @param file
     */
    void write(File file);

    /**
     * 写出到当前文件
     */
    void write();


}
