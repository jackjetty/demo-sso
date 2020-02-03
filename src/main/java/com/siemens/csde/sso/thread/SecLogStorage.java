package com.siemens.csde.sso.thread;

import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecLogStorage {

    private final int MAX_SIZE = 100;
    private LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(MAX_SIZE);
    private static SecLogStorage instance = new SecLogStorage();

    private SecLogStorage() {
    }

    public static SecLogStorage getInstance() {
        return instance;
    }

    public void produce(String seclog) {
        if (list.size() == MAX_SIZE) {
            log.info("seclog库存量为" + MAX_SIZE + ",不能再继续生产！");
        }
        try {
            list.put(seclog);
            log.info("生产SecLog：" + seclog);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String consume() {
        String entity = null;
        if (list.isEmpty()) {
            log.info("seclog库存量为0，不能再继续消费！");
        }
        try {
            entity = list.take();
            log.info("消费SecLog：" +entity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return entity;
    }
}