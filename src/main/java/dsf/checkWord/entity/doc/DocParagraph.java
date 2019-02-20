package dsf.checkWord.entity.doc;

import dsf.checkWord.entity.WordParagraph;
import dsf.checkWord.entity.WordRun;
import org.apache.poi.hwpf.usermodel.LineSpacingDescriptor;
import org.apache.poi.hwpf.usermodel.Paragraph;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class DocParagraph implements WordParagraph {

    private final Paragraph paragraph;

    public DocParagraph(Paragraph paragraph) {

        this.paragraph = paragraph;
    }


    @Override
    public int getNumCharacterRuns() {
        return paragraph.numCharacterRuns();
    }


    /**
     * TODO 可以维护一个集合 存放run 不必每次都要通过 {@code paragraph} 读取
     * @return
     */
    @Override
    public int getNonEmptyRunNum() {

        int num = 0;
        for(int i=0; i>getNumCharacterRuns(); i++) {

            if(!getCharacterRun(i).isEmpty()) {

                num++;
            }
        }
        return num;
    }

    @Override
    public WordRun getCharacterRun(int index) {
        return new DocRun(paragraph.getCharacterRun(index));
    }

    @Override
    public String text() {
        return paragraph.text();
    }

    @Override
    public int getFirstLineIndent() {
        return paragraph.getFirstLineIndent();
    }

    @Override
    public int getLineSpacing() {

        int i = paragraph.getLineSpacing().toInt();
        int lower = i >>> 16;
        int upper = i & 0xffff;
//        System.out.println("lower " + lower);
//        System.out.println("upper " + upper);
        return upper;
    }

    @Override
    public String getStyleIndex() {
//        System.out.println("styleIndex = " + paragraph.getStyleIndex());

        return String.valueOf(paragraph.getStyleIndex());
    }

    @Override
    public int getIndentFromLeft() {
        return paragraph.getIndentFromLeft();
    }

    @Override
    public int getFontAlignment() {
        return paragraph.getFontAlignment();
    }

    @Override
    public int getLvl() {
        return paragraph.getLvl();
    }

    @Override
    public void merge() {

        int numCharacterRuns = getNumCharacterRuns();
        WordRun last = getCharacterRun(0), current = null;

        for(int i = 1; i < numCharacterRuns; i++) {

            if((current = getCharacterRun(i)) == null) {

                continue;
            }

            if(current.equals(last)) {

                last.replaceText(last.text() + current.text(), true);
//                last.replaceText(last.text(), last.text() + current.text());

                current.replaceText("", true);
            } else {

                last = current;
            }

        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void show() {

        int numWordRuns = getNumCharacterRuns();
//        String styleIndex = paragraph.getStyleIndex();
//        System.out.println("paragraph type = " + styleIndex);
//        StyleSheet styleS0heet = word.getStyleSheet();
//        StyleDescription styleDescription = styleSheet.getStyleDescription(Integer.valueOf(styleIndex));
        int lvl = paragraph.getLvl();
        System.out.println("大纲级别 " + lvl);
        System.out.println("行间距 " + paragraph.getLineSpacing());
        System.out.println("首行缩进 " + paragraph.getFirstLineIndent());
        System.out.println("对齐 " + paragraph.getFontAlignment());
        for(int j=0; j<numWordRuns; j++) {

//            System.out.println("baseStyle = " + styleDescription.getBaseStyle());
//            System.out.println("styleName = " + styleDescription.getName());
            WordRun wordRun = getCharacterRun(j);
            wordRun.show();

        }
    }

    @Override
    public void setColor(int color) {

        int numCharacterRuns = getNumCharacterRuns();
        for(int i=0; i<numCharacterRuns; i++) {

            getCharacterRun(i).setColor(color);
        }
    }
}
