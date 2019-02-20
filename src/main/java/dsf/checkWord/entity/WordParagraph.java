package dsf.checkWord.entity;

import org.apache.poi.hwpf.usermodel.LineSpacingDescriptor;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public interface WordParagraph {

    /**
     * 获取run数
     * @return
     */
    int getNumCharacterRuns();

    /**
     * 返回非空run数
     * @return
     */
    int getNonEmptyRunNum();

    /**
     * 获取index处的run
     * @param index
     * @return
     */
    WordRun getCharacterRun(int index);

    /**
     * 文本
     * @return
     */
    String text();

    /**
     * 获取首行缩进
     * @return
     */
    int getFirstLineIndent();

    /**
     * 获取行间距
     * @return
     */
    int getLineSpacing();

    /**
     * 获取样式
     * @return
     */
    String getStyleIndex();

    /**
     * @return
     */
    int getIndentFromLeft();

    /**
     * @return
     */
    int getFontAlignment();


    /**
     * 获取大纲级别
     * @return
     */
    int getLvl();

    /**
     * 将一个段落中相同样式的合并
     */
    void merge();

    /**
     * 判断是否 为空段
     * @return
     */
    boolean isEmpty();

    /**
     * 展示
     */
    void show();

    /**
     * 设置颜色
     */
    void setColor(int color);

}
