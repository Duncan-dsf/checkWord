package dsf.checkWord.entity;

import java.util.HashMap;

/**
 * @author 董少飞
 * @date 2018/10/16
 */
public class WordParagraphMoc implements WordParagraph {
    public void setFirstLineIndent(int firstLineIndent) {
        this.firstLineIndent = firstLineIndent;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setStyleIndex(String styleIndex) {
        this.styleIndex = styleIndex;
    }

    public void setIndentFromLeft(int indentFromLeft) {
        this.indentFromLeft = indentFromLeft;
    }

    public void setFontAlignment(int fontAlignment) {
        this.fontAlignment = fontAlignment;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public WordParagraphMoc(HashMap<String, String> map) {

        setFirstLineIndent(Integer.valueOf(map.get("标题级别")));
//        setFontAlignment();
    }

    @Override
    public int getNumCharacterRuns() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNonEmptyRunNum() {
        return 0;
    }

    @Override
    public WordRun getCharacterRun(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String text() {
        return null;
    }

    private int firstLineIndent;

    @Override
    public int getFirstLineIndent() {
        return firstLineIndent;
    }

    private int lineSpacing;

    @Override
    public int getLineSpacing() {
        return lineSpacing;
    }

    private String styleIndex;

    @Override
    public String getStyleIndex() {
        return null;
    }

    private int indentFromLeft;

    @Override
    public int getIndentFromLeft() {
        return 0;
    }

     private int fontAlignment;

    @Override
    public int getFontAlignment() {
        return 0;
    }

    private int lvl;

    @Override
    public int getLvl() {
        return 0;
    }

    @Override
    public void merge() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void setColor(int color) {

    }
}
