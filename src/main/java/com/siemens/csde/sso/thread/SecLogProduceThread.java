package com.siemens.csde.sso.thread;
public class SecLogProduceThread implements Runnable {
    String entity = null;

    public SecLogProduceThread(String entity) {
        this.entity = entity;
    }

    @Override
    public void run() {
        SecLogStorage.getInstance().produce(entity);
    }
}