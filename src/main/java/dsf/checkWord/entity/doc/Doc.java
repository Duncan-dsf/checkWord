package dsf.checkWord.entity.doc;

import dsf.checkWord.entity.AbstractWord;
import dsf.checkWord.entity.WordParagraph;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class Doc extends AbstractWord {

    public final HWPFDocument hwpfDocument;


//    public Doc(HWPFDocument hwpfDocument) {
//
//        if(hwpfDocument == null) {
//
//            throw new RuntimeException("");
//        }
//        this.hwpfDocument = hwpfDocument;
//    }

    public Doc(String file) throws IOException {

        super(file);
        hwpfDocument = new HWPFDocument(new POIFSFileSystem(new File(file), false));
        initParagraphMap();
    }

    @Override
    public void test() {

        Paragraph paragraph = hwpfDocument.getOverallRange().getParagraph(0);
        CharacterRun characterRun = paragraph.getCharacterRun(0);
        characterRun.replaceText("插入的文字", true);
    }

    @Override
    public List<Picture> getImages() {

        return hwpfDocument.getPicturesTable().getAllPictures();
    }

    @Override
    public int getNumParagraphs() {
        return hwpfDocument.getOverallRange().numParagraphs();
    }

    @Override
    public WordParagraph getParagraph(int index) {
        return new DocParagraph(hwpfDocument.getOverallRange().getParagraph(index));
    }

    @Override
    public StyleSheet getStyleSheet() {
        return hwpfDocument.getStyleSheet();
    }

    @Override
    public void show() {

    }

    @Override
    public String text() {

        return hwpfDocument.getOverallRange().text();
    }

    @Override
    public void write(File file) {

        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            hwpfDocument.write(fileOutputStream);
//            fileOutputStream.flush();
        } catch (IOException e) {

            e.printStackTrace();
        }
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream(file);
//            hwpfDocument.write(fileOutputStream);
//            fileOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(fileOutputStream != null) {
//                    fileOutputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if(fileOutputStream != null) {
//
//            try {
//                fileOutputStream.getFD().sync();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void write() {

        try {
            hwpfDocument.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
