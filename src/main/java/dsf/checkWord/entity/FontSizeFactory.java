package dsf.checkWord.entity;

import java.util.HashMap;

/**
 * @author 董少飞
 * @date 2018/10/7
 */
public class FontSizeFactory {

    private static final HashMap<Integer, String> DOC_MAP = new HashMap<>(32);
    private static final HashMap<Integer, String> DOCX_MAP = new HashMap<>(32);

    static {
//        DOC_MAP.put(63, "大特号");
//        DOC_MAP.put(54, "特号");
        DOC_MAP.put(84, "初号");
        DOC_MAP.put(72, "小初号");
//        DOC_MAP.put(31.5, "大一号");
        DOC_MAP.put(52, "一号");
        DOC_MAP.put(48, "小一");
        DOC_MAP.put(44, "二号");
        DOC_MAP.put(36, "小二号");
        DOC_MAP.put(32, "三号");
        DOC_MAP.put(30, "小三");
        DOC_MAP.put(28, "四号");
        DOC_MAP.put(24, "小四号");
        DOC_MAP.put(21, "五号");
        DOC_MAP.put(18, "小五号");
        DOC_MAP.put(15, "六号");
        DOC_MAP.put(13, "小六号");
        DOC_MAP.put(11, "七号");
        DOC_MAP.put(10, "八号");
    }

    static {
//        DOCX_MAP.put(63, "大特号");
//        DOCX_MAP.put(54, "特号");
        DOCX_MAP.put(42, "初号");
        DOCX_MAP.put(36, "小初号");
//        DOCX_MAP.put(31.5, "大一号");
        DOCX_MAP.put(26, "一号");
        DOCX_MAP.put(24, "小一");
        DOCX_MAP.put(22, "二号");
        DOCX_MAP.put(18, "小二号");
        DOCX_MAP.put(16, "三号");
        DOCX_MAP.put(15, "小三");
        DOCX_MAP.put(14, "四号");
        DOCX_MAP.put(12, "小四号");
        DOCX_MAP.put(-1, "五号");
        DOCX_MAP.put(9, "小五号");
        DOCX_MAP.put(7, "六号");
        DOCX_MAP.put(6, "小六号");
        DOCX_MAP.put(5, "七号");
//        DOCX_MAP.put(5, "八号");
    }

    private static final FontSize DOC_FONT_SIZE = new FontSize(DOC_MAP);

    private static final FontSize DOCX_FONT_SIZE = new FontSize(DOCX_MAP);

    public static FontSize getDocFontSize() {
        return DOC_FONT_SIZE;
    }

    public static FontSize getDocxFontSize() {
        return DOCX_FONT_SIZE;
    }
}
