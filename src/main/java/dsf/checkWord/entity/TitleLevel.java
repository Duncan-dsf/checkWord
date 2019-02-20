package dsf.checkWord.entity;

import java.util.HashMap;

/**
 * @author 董少飞
 * @date 2018/10/17
 */
public class TitleLevel {

    private static final HashMap<String, Integer> map = new HashMap<>(16);

    static {
        map.put("1级", 0);
        map.put("2级", 1);
        map.put("3级", 2);
        map.put("4级", 3);
        map.put("5级", 4);
        map.put("6级", 5);
        map.put("7级", 6);
        map.put("8级", 7);
        map.put("9级", 8);
        map.put("正文", 9);
    }

    public static int getLevel(String name) {

        return map.get(name);
    }

    public static String getLevel(int id) {

        if(id == 9) {

            return "正文";
        } else {

            return id-1 + "级";
        }
    }
}
