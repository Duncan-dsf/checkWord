package dsf.checkWord.entity.docx;

import dsf.checkWord.entity.FontSize;
import dsf.checkWord.entity.FontSizeFactory;
import dsf.checkWord.entity.WordRun;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class DocxRun implements WordRun {

    private final XWPFRun xwpfRun;
    private final FontSize fontSize = FontSizeFactory.getDocxFontSize();

    public DocxRun(XWPFRun xwpfRun) {

        this.xwpfRun = xwpfRun;
    }

    @Override
    public String getFontSize() {

        return fontSize.getFontSize(xwpfRun.getFontSize());
//        return String.valueOf();
    }

    @Override
    public String getFontName() {
        return xwpfRun.getFontName();
    }

    @Override
    public int getColor() {
//        return xwpfRun.getColor();
        return 0;
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public boolean isBold() {
        return xwpfRun.isBold();
    }

    @Override
    public boolean isItalic() {
        return xwpfRun.isItalic();
    }

    @Override
    public int type() {
        return 0;
    }

    @Override
    public int getStyleIndex() {
        return 0;
    }

    @Override
    public String text() {
        return xwpfRun.text();
    }

    @Override
    public void replaceText(String newText, boolean addAfter) {

    }

    @Override
    public void replaceText(String pPlaceHolder, String pValue) {

    }

    @Override
    public void delete() {

    }

    @Override
    public boolean emptyAndDelete() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public boolean equals(WordRun wordRun) {
        return false;
    }
}
