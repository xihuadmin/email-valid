package com.zjzc.manage.core.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 初始线程池
 */
public class ThreadPool {

    private static ExecutorService service;

    private static final Integer POOL_SIZE = 10;

    public static ExecutorService getService(){
        if(service == null){
            service = Executors.newFixedThreadPool(POOL_SIZE);
        }
        return service;
    }
}
