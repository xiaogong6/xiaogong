package com.xiaogong.utils;

import java.util.concurrent.*;

/**
 * 线程池工具类
 * @author 111
 */
public class ThreadPoolUtils {

    private ThreadPoolUtils(){};

    private static ExecutorService exec = new ThreadPoolExecutor(
            10,
            20,
            5L, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>(100),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static ExecutorService getPool(){
        return exec;
    }

    /**
     * 子线程执行结束future.get()返回null，若没有执行完毕，主线程将会阻塞等待
     * @param command
     * @return
     */
    public static Future submit(Runnable command) {
        return exec.submit(command);
    }

    /**
     * 子线程中的返回值可以从返回的future中获取：future.get();不会阻塞
     * @param command
     * @return
     */
    public static Future submit(Callable command) {
        return exec.submit(command);
    }
}
