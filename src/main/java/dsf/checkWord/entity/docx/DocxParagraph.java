package dsf.checkWord.entity.docx;

import dsf.checkWord.entity.Word;
import dsf.checkWord.entity.WordParagraph;
import dsf.checkWord.entity.WordRun;
import dsf.checkWord.entity.doc.DocRun;
import org.apache.poi.hwpf.usermodel.LineSpacingDescriptor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.math.BigInteger;

import static sun.security.krb5.Confounder.intValue;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class DocxParagraph implements WordParagraph {

    private final XWPFParagraph xwpfParagraph;

    private final XWPFDocument xwpfDocument;

    public DocxParagraph(XWPFParagraph xwpfParagraph, XWPFDocument xwpfDocument) {

        this.xwpfParagraph = xwpfParagraph;
        this.xwpfDocument = xwpfDocument;
    }

    @Override
    public int getNumCharacterRuns() {
        return xwpfParagraph.getRuns().size();
    }

    @Override
    public int getNonEmptyRunNum() {
        return 0;
    }

    @Override
    public WordRun getCharacterRun(int index) {
        return new DocxRun(xwpfParagraph.getRuns().get(index));
    }

    @Override
    public String text() {
        return xwpfParagraph.getText();
    }

    @Override
    public int getFirstLineIndent() {
        return xwpfParagraph.getFirstLineIndent();
    }

    @Override
    public int getLineSpacing() {
        return 0;
    }

    @Override
    public String getStyleIndex() {
        return xwpfParagraph.getStyleID();
//        return 0;
    }

    @Override
    public int getIndentFromLeft() {
        return 0;
    }

    @Override
    public int getFontAlignment() {
        return 0;
    }

    @Override
    public int getLvl() {
        BigInteger val = xwpfDocument.getStyles().getStyle(getStyleIndex()).getCTStyle().getPPr().getOutlineLvl().getVal();
        return val.intValue();
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
