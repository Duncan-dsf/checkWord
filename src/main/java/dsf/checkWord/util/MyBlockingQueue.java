package dsf.checkWord.util;

import dsf.checkWord.dto.WordMap;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 董少飞
 * @date 2018/10/15
 */
public class MyBlockingQueue {

    private static final BlockingQueue<WordMap> MAP_BLOCKING_QUEUE = new ArrayBlockingQueue<>(200);

    public static BlockingQueue<WordMap> getMapBlockingQueue() {

        return MAP_BLOCKING_QUEUE;
    }
}
