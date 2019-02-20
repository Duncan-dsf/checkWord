package dsf.checkWord.util;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 董少飞
 * @date 2018/10/15
 */
public class MyThreadPoolExecutor {

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(0, 100, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static ThreadPoolExecutor getMyThreadPoolExecutor() {

        return THREAD_POOL_EXECUTOR;
    }
}
