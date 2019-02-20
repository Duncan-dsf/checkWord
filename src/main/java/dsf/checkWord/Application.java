package dsf.checkWord;

import dsf.checkWord.entity.*;
import dsf.checkWord.entity.doc.Doc;
import dsf.checkWord.outlook.Homepage;
import dsf.checkWord.outlook.Uploadfile;
import dsf.checkWord.service.WordAnalyzer;
import dsf.checkWord.service.WordAnalyzerFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.*;
import org.apache.poi.hwpf.usermodel.HWPFList;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] a) throws IOException, NoSuchFieldException, IllegalAccessException {

//        testDocx();
//        testStyleDocx();
//        testStyleDoc();
//        test();
//        testCataDoc();
//        testTitleDocx();
//        testTitleDoc();
//        testColorDoc();
//        Uploadfile.main(null);
//        System.out.println(Runtime.getRuntime().availableProcessors());

//        testLong();

//        Homepage.main(null);
        testDocxColor();
//        testImage();
//        testStyleIndex();
//        testLevel();
//        testRun();



    }

    public static void testDocxColor() {
        try (XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.docx"))) {

            xwpfDocument.getParagraphs().get(0).getRuns().get(0).setColor(null);
            xwpfDocument.write(new FileOutputStream("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿1.docx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testDoc() {

//        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.doc");
        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\test.doc");

        wordAnalyzer.analysis();
    }

    public static void testDocx() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\test.docx");
        wordAnalyzer.analysis();

    }

    public static void testStyleDocx() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\testStyle.docx");
        wordAnalyzer.analysis();
    }

    public static void testStyleDoc() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\testStyle.doc");
        wordAnalyzer.analysis();
    }

    public static void test() {
        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.doc");
        wordAnalyzer.analysis();
    }

    public static void testLong() {
        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第7部分：数字数据输入输出串行传输 报批稿.doc");
        wordAnalyzer.analysis();
    }

    public static void testCataDocx() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\cata.docx");
        wordAnalyzer.analysis();
    }

    public static void testCataDoc() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\cata.doc");
        wordAnalyzer.analysis();
    }

    public static void testTitleDocx() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\title.docx");
        wordAnalyzer.analysis();
    }

    public static void testTitleDoc() {

        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\title.doc");
        wordAnalyzer.analysis();
    }

    public static void testColorDoc() {

//        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.doc");
        WordAnalyzer wordAnalyzer = WordAnalyzerFactory.getWordAnalyzer("F:\\idea\\checkWord\\测试文件\\testColor.doc");

        wordAnalyzer.analysis();
    }

    public static void testSetColor() {

        Word word = WordFactory.getWord("F:\\idea\\checkWord\\测试文件\\testColor.doc");
//        word.test();
        for(int i=0; i<word.getNumParagraphs(); i++) {

            word.getParagraph(i).setColor(6);
        }
//        File file = new File("F:\\idea\\checkWord\\测试文件\\test_result.doc");
        word.write();
//        file.createNewFile();
    }

    public static void testStyleIndex() throws NoSuchFieldException, IllegalAccessException {

        Word word = WordFactory.getWord("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.doc");
        StyleSheet styleSheet = word.getStyleSheet();

        for(int numParagraphs=0; numParagraphs<word.getNumParagraphs(); numParagraphs++) {

            WordParagraph paragraph = word.getParagraph(numParagraphs);
            String styleIndex1 = paragraph.getStyleIndex();
            System.out.println("段" + numParagraphs + " " + styleIndex1);
            System.out.println(paragraph.text());
            StyleDescription styleDescription = styleSheet.getStyleDescription(Integer.valueOf(styleIndex1));
            for(int numRuns=0; numRuns<paragraph.getNumCharacterRuns(); numRuns++) {

                WordRun characterRun = paragraph.getCharacterRun(numRuns);
                int styleIndex = characterRun.getStyleIndex();
                if(styleIndex != Integer.valueOf(styleIndex1)) {

                    System.out.println("    " + styleIndex);
                    StyleDescription styleDescription1 = styleSheet.getStyleDescription(styleIndex);
//                    Field name = StyleDescription.class.getDeclaredField("_name");
//                    Object o = name.get(styleDescription);
                    System.out.println("    " + styleDescription1);
                    System.out.println(">>>>>>");
                }
            }
        }
    }

    public static void testImage() {

        Word word = WordFactory.getWord("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.doc");
        List<Picture> images = word.getImages();
        for(Picture picture : images) {

            picture.getDescription();
        }
    }

    public static void testLevel() {

        Word word = WordFactory.getWord("F:\\idea\\checkWord\\测试文件\\3 舰船系统界面要求 电子信息 第6部分：载波侦听多路访问冲突检测多路复用分布式结构数据总线 报批稿.doc");
        HWPFDocument hwpfDocument = ((Doc) word).hwpfDocument;

        Range overallRange = hwpfDocument.getOverallRange();
        int numParagraphs = overallRange.numParagraphs();
        for(int i=0; i<numParagraphs; i++) {

            Paragraph paragraph = overallRange.getParagraph(i);
            if(paragraph.isInList()) {

                HWPFList list = paragraph.getList();
//                ListData listData = list.getListData();
//                if(listData != null) {
//
//                    ListLevel[] levels = listData.getLevels();
//                    for(int j=0; j<levels.length; j++) {
//
//                        ListLevel listLevel = levels[j];
//                        int numberFormat = listLevel.getNumberFormat();
//                        byte[] bytes = listLevel.getNumberText().getBytes();
//                        System.out.println(Arrays.toString(bytes));
//                        System.out.println(listLevel);
//                    }
//                }
                LFO lfo = list.getLFO();
                LFOData lfoData = list.getLFOData();
                int ilfo = paragraph.getIlfo();
                int ilvl = paragraph.getIlvl();
                int lvl = paragraph.getLvl();
                if(lvl != 0) {

                    ListLevel level = list.getListData().getLevel(lvl);
                    System.out.println(ilfo);
                    System.out.println(ilvl);
                    System.out.println();
                }
//                System.out.println(lfo);
//                System.out.println(lfoData);
            }
        }



    }


    /**
     * 通过doc获得listTable
     * 不起作用
     * @param listTables
     */
    public static void getLevelByDoc(ListTables listTables) {

        for(int i=0; i<10; i++) {

            ListData listData = listTables.getListData(i);
            if(listData != null) {

                for(ListLevel listLevel : listData.getLevels()) {

                    int numberFormat = listLevel.getNumberFormat();
                    System.out.println(listLevel);
                }
            }
        }
    }

    public static void testParagraph() {

        Word word = WordFactory.getWord("F:\\idea\\checkWord\\测试文件\\testParagraph.doc");
        for(int i=0; i<word.getNumParagraphs(); i++) {
            System.out.println(word.getParagraph(i).text());
        }
    }

    public static void testRun() {

        Word word = WordFactory.getWord("F:\\idea\\checkWord\\测试文件\\testRun.doc");
        WordParagraph paragraph = word.getParagraph(0);
        for(int i=0; i<paragraph.getNumCharacterRuns(); i++) {

            System.out.println(paragraph.getCharacterRun(i).text());
        }
    }
}
