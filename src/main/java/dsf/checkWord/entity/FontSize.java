package dsf.checkWord.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 董少飞
 * @date 2018/10/7
 */
public class FontSize {

    private final HashMap<Integer, String> FONT_SIZE_MAP;


    FontSize(HashMap<Integer, String> map) {

        FONT_SIZE_MAP = map;
    }


    public String getFontSize(int size) {

        return FONT_SIZE_MAP.get(size);
    }

    public double getFontSize(String fontSize) {

        Set<Map.Entry<Integer, String>> entries = FONT_SIZE_MAP.entrySet();

        for(Map.Entry<Integer, String> entry : entries) {

            if(entry.getValue().equals(fontSize)) {

                return entry.getKey();
            }
        }
        return 0;
    }
}
