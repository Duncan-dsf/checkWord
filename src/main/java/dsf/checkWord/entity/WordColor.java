package dsf.checkWord.entity;

import java.util.HashMap;

/**
 * @author 董少飞
 * @date 2018/10/17
 */
public class WordColor {

    private static final HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("深红", 13);
        map.put("红色", 6);
        map.put("橙色", 7);
        map.put("黄色", 7);
        map.put("浅绿", 7);
        map.put("绿色", 11);
        map.put("浅蓝", 3);
        map.put("蓝色", 3);
        map.put("深蓝", 9);
        map.put("紫色", 5);
        map.put("黑色", 0);
    }

    public static int getColor(String name) {

        return map.get(name);
    }
}
