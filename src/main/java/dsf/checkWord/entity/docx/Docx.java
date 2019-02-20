package dsf.checkWord.entity.docx;

import dsf.checkWord.dto.AbstractWordMap;
import dsf.checkWord.entity.AbstractWord;
import dsf.checkWord.entity.Word;
import dsf.checkWord.entity.WordParagraph;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class Docx extends AbstractWord {

    private final XWPFDocument xwpfDocument;

//    public Docx(XWPFDocument xwpfDocument) {
//
//        this.xwpfDocument = xwpfDocument;
//    }

    public Docx(String file) throws IOException {

        super(file);
        xwpfDocument = new XWPFDocument(new FileInputStream(file));
        initParagraphMap();
    }

    @Override
    public List<Picture> getImages() {
        return null;
    }

    @Override
    public void test() {

    }

    @Override
    public int getNumParagraphs() {
        return xwpfDocument.getParagraphs().size();
    }

    @Override
    public WordParagraph getParagraph(int index) {
        return new DocxParagraph(xwpfDocument.getParagraphs().get(index), xwpfDocument);
    }

    @Override
    public StyleSheet getStyleSheet() {
//        return xwpfDocument.getStyles();
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public String text() {
        return null;
    }

    @Override
    public void write(File file) {

    }

    @Override
    public void write() {

    }
}
