package dsf.checkWord.entity;

import java.util.*;

/**
 * @author 董少飞
 * @date 2018/10/17
 */
public class ParagraphRule {

    private final int lvl;
    private final String fontName;
    private final String fontSize;
    private final int color;

    /**
    * 不符合要求的word需要标出
     * 表示以什么颜色标出
    */
    private final int ERROR_COLOR = WordColor.getColor("红色");

    /**
     * TODO fontSize 此处默认用doc版 日后完善
     * @param rule
     */
    public ParagraphRule(Map<String, String> rule) {

        if(rule == null) {
            throw new IllegalArgumentException("rule不能为空");
        }
        lvl = TitleLevel.getLevel(rule.get("标题级别"));
        fontName = rule.get("字体");
        fontSize = rule.get("字号");
        color = WordColor.getColor(rule.get("颜色"));
    }

    public String getFontName() {
        return fontName;
    }

    public String getFontSize() {
        return fontSize;
    }

    public int getColor() {
        return color;
    }

    public int getLvl() {

        return lvl;
    }

    public Map<WordRun, String> check(WordParagraph wordParagraph) {

        if(wordParagraph == null) {

            System.out.println("wordParagraph 为 null");
            return null;
        }

        Map<WordRun, String> map = new LinkedHashMap<>();

        int lvl = wordParagraph.getLvl();
        if(this.lvl != lvl) {


//            map.put(null, "段落级别不符 要求:"+this.lvl+" 当前: "+lvl);
            map.put(null, "段落级别不符 " + "当前: "+lvl);
            wordParagraph.setColor(ERROR_COLOR);
        }

        WordRun wordRun = null;
        for(int i=0; i<wordParagraph.getNumCharacterRuns(); i++) {

            if(!(wordRun = wordParagraph.getCharacterRun(i)).isEmpty()) {

                StringBuilder mes = new StringBuilder();

                checkRun(mes, wordRun);

                if(!mes.toString().trim().equals("")) {

                    map.put(wordRun, mes.toString());
                    wordRun.setColor(ERROR_COLOR);
                }
            }
        }
        return map;
    }

    private void checkRun(StringBuilder mes, WordRun wordRun) {

        int color = wordRun.getColor();
        String fontName = wordRun.getFontName();
        String fontSize = wordRun.getFontSize();

        /*if(this.color != color) {

            mes.append("颜色不符 要求:").append(this.color).append("当前: ").append(color);
        }

        if(this.fontName != null && !this.fontName.equals(fontName)) {

            mes.append("字体不符 要求:").append(this.fontName).append(" 当前: ").append(fontName);
        }

        if(this.fontSize != null && !this.fontSize.equals(fontSize)) {

            mes.append("字号不符 要求:").append(this.fontSize).append(" 当前:").append(fontSize);
        }*/

        if(this.color != color) {

            mes.append("颜色不符 ").append("当前: ").append(color);
        }

        if(this.fontName != null && !this.fontName.equals(fontName)) {

            mes.append("字体不符 ").append(" 当前: ").append(fontName);
        }

        if(this.fontSize != null && !this.fontSize.equals(fontSize)) {

            mes.append("字号不符 ").append(" 当前:").append(fontSize);
        }


    }

    private void appendErrorMessage(StringBuilder stringBuilder) {


    }
}
