package com.siemens.csde.sso.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private static ThreadPoolManager instance = new ThreadPoolManager();

    private ExecutorService secLogThreadPool;
    private ExecutorService sysLogThreadPool;

    public ExecutorService getSysLogThreadPool() {
        return sysLogThreadPool;
    }

    public void setSysLogThreadPool(ExecutorService sysLogThreadPool) {
        this.sysLogThreadPool = sysLogThreadPool;
    }

    public ExecutorService getSecLogThreadPool() {
        return secLogThreadPool;
    }

    public void setSecLogThreadPool(ExecutorService secLogThreadPool) {
        this.secLogThreadPool = secLogThreadPool;
    }

    public static ThreadPoolManager getInstance(){
        return instance;
    }

    private ThreadPoolManager() {
        secLogThreadPool = new ThreadPoolExecutor(1, 3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadPoolExecutor.CallerRunsPolicy());
        sysLogThreadPool = Executors.newFixedThreadPool(3);
    }

}