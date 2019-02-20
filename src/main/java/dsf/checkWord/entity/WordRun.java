package dsf.checkWord.entity;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public interface WordRun {

    /**
     * 获取字体大小
     * @return
     */
    String getFontSize();

    /**
     * 获取字体名
     * @return
     */
    String getFontName();

    /**
     * 获取颜色
     * @return
     */
    int getColor();

    /**
     * 设置颜色
     * @param color 颜色
     */
    void setColor(int color);

    /**
     * 判断是否加粗
     * @return
     */
    boolean isBold();

    /**
     * 判断是否斜体
     * @return
     */
    boolean isItalic();

    /**
     * 获取类型
     * @return
     */
    int type();

    int getStyleIndex();

    /**
     * 获取文本
     * @return
     */
    String text();

    /**
     * 用新字符串替代
     * @param newText
     * @param addAfter
     */
    void replaceText( String newText, boolean addAfter );

    /**
     * 替代
     * @param pPlaceHolder
     * @param pValue
     */
    void replaceText(String pPlaceHolder, String pValue);

    /**
     * 删除
     */
    void delete();

    /**
     * 删除text为空串、回车符、换行符 的run
     * @return  时 删除 并返回 true
     */
    boolean emptyAndDelete();

    /**
     * 判断text是否为为空串、回车符、换行符
     * @return
     */
    boolean isEmpty();

    /**
     * 展示
     */
    void show();

    /**
     * 判断两个run样式是否相同
     * @param wordRun
     * @return
     */
    boolean equals(WordRun wordRun);
}
