package dsf.checkWord.service;

import dsf.checkWord.entity.Word;
import dsf.checkWord.entity.WordParagraph;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class WordAnalyzerImpl implements WordAnalyzer {

    private final Word word;

    public WordAnalyzerImpl(Word word) {

        this.word = word;
    }


    @Override
    public void analysis() {

        int numParagraphs = word.getNumParagraphs();
        for(int i=0; i<numParagraphs; i++) {

            WordParagraph paragraph = word.getParagraph(i);
            paragraph.merge();
            paragraph.show();
        }
    }

//    protected void showParagraph(WordParagraph paragraph) {
//
//        int numWordRuns = paragraph.getNumCharacterRuns();
////        String styleIndex = paragraph.getStyleIndex();
////        System.out.println("paragraph type = " + styleIndex);
////        StyleSheet styleSheet = word.getStyleSheet();
////        StyleDescription styleDescription = styleSheet.getStyleDescription(Integer.valueOf(styleIndex));
//        int lvl = paragraph.getLevel();
//        System.out.println("大纲级别 " + lvl);
//        System.out.println("行间距 " + paragraph.getLineSpacing());
//        System.out.println("首行缩进 " + paragraph.getFirstLineIndent());
//        System.out.println("对齐 " + paragraph.getFontAlignment());
//        for(int j=0; j<numWordRuns; j++) {
//
////            System.out.println("baseStyle = " + styleDescription.getBaseStyle());
////            System.out.println("styleName = " + styleDescription.getName());
//            WordRun wordRun = paragraph.getCharacterRun(j);
//            showWordRun(wordRun);
//
//        }
//    }
//
//    protected void showWordRun(WordRun wordRun) {
//
//        if(!wordRun.isEmpty()) {
//
////            System.out.println("run type = " + wordRun.type());
//            System.out.println("text = " + wordRun.text());
//            String fontName = wordRun.getFontName();
//            String fontSize = wordRun.getFontSize();
//            int color = wordRun.getColor();
//            System.out.println("fontName " + fontName);
//            System.out.println("fontSize " + fontSize);
//            System.out.println("color " + color);
//            System.out.println(wordRun.isBold() ? "粗体" : "非粗体");
//            System.out.println(wordRun.isItalic() ? "斜体" : "非斜体");
//            System.out.println();
//        }
//    }
}
