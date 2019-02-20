package dsf.checkWord.entity.doc;

import dsf.checkWord.entity.FontSize;
import dsf.checkWord.entity.FontSizeFactory;
import dsf.checkWord.entity.WordRun;
import org.apache.poi.hwpf.usermodel.CharacterRun;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class DocRun implements WordRun {

    private final CharacterRun characterRun;

    private final FontSize fontSize = FontSizeFactory.getDocFontSize();

    public DocRun(CharacterRun characterRun) {

//        this.characterRun = new CharacterRun();
        this.characterRun = characterRun;
    }

    @Override
    public String getFontSize() {
        return fontSize.getFontSize(characterRun.getFontSize());
    }

    @Override
    public String getFontName() {
        return characterRun.getFontName();
    }

    @Override
    public int getColor() {
        return characterRun.getColor();
    }

    @Override
    public void setColor(int color) {
        characterRun.setColor((byte) color);
    }

    @Override
    public boolean isBold() {
        return characterRun.isBold();
    }

    @Override
    public boolean isItalic() {

        return characterRun.isItalic();
    }

    @Override
    public int type() {
        return characterRun.type();
    }

    @Override
    public int getStyleIndex() {
        return characterRun.getStyleIndex();
    }

    @Override
    public String text() {
        return characterRun.text();
    }

    @Override
    public void replaceText(String newText, boolean addAfter) {

        characterRun.replaceText(newText, addAfter);
    }

    @Override
    public void replaceText(String pPlaceHolder, String pValue) {


    }

    @Override
    public void delete() {
        characterRun.delete();
    }

    @Override
    public boolean emptyAndDelete() {

        String text = text();

        if(text == null || "".equals(text) || "\n".equals(text) || "\r".equals(text)) {

            System.out.println("null");
            characterRun.delete();

            return true;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {

        String text = text();
        if(text == null || "".equals(text) || "\n".equals(text) || "\r".equals(text)) {

            return true;
        } else {

            return false;
        }
    }

    @Override
    public void show() {

        if(!this.isEmpty()) {

//            System.out.println("run type = " + this.type());
            System.out.println("text = " + this.text());
            String fontName = this.getFontName();
            String fontSize = this.getFontSize();
            int color = this.getColor();
            System.out.println("fontName " + fontName);
            System.out.println("fontSize " + fontSize);
            System.out.println("color " + color);
            System.out.println(this.isBold() ? "粗体" : "非粗体");
            System.out.println(this.isItalic() ? "斜体" : "非斜体");
            System.out.println();
        }
    }

    private final Set<String> EXCLUDED_METHOD = new HashSet<>(8);

    {
        EXCLUDED_METHOD.add("equals");
        EXCLUDED_METHOD.add("text");
        EXCLUDED_METHOD.add("replaceText");
        EXCLUDED_METHOD.add("delete");
        EXCLUDED_METHOD.add("emptyAndDelete");
        EXCLUDED_METHOD.add("show");
        EXCLUDED_METHOD.add("isEmpty");
        EXCLUDED_METHOD.add("setColor");
    }

    @Override
    public boolean equals(WordRun wordRun) {

        if(wordRun == null) {

            return false;
        }

        Class<? extends DocRun> aClass = this.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for(Method method : declaredMethods) {

//            System.out.println(method.getName());
            if(EXCLUDED_METHOD.contains(method.getName())) {

                continue;
            }
            Object result1 = null, result2 = null;
            try {
                result1 = method.invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            try {
                result2 = method.invoke(wordRun);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            if(result1 == result2 ||
                    (result1 != null && result1.equals(result2))) {

            } else {
                System.out.println("判断样式是否相同 method " + method.getName());
                System.out.println("result1 = " + result1);
                System.out.println("result2 = " + result2);
                return false;
            }
            new PriorityQueue<Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return 0;
                }
            });
        }
        return true;
    }
}
