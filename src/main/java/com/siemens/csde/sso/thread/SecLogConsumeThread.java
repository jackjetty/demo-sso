package com.siemens.csde.sso.thread;

public class SecLogConsumeThread implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setName("消费 thread");
        while(true){
            //TODO do something here
            SecLogStorage.getInstance().consume();
        }
    }
}