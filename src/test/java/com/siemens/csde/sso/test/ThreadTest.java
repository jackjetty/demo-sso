package com.siemens.csde.sso.test;

import com.siemens.csde.sso.thread.SecLogConsumeThread;
import com.siemens.csde.sso.thread.SecLogProduceThread;
import com.siemens.csde.sso.thread.ThreadPoolManager;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class ThreadTest{
    @Test
    public void testBlockQueue() throws  Exception{

        String log1="001";

        String log2="002";

        String log3="003";

        String log4="004";

        String log5="005";

        String log6="006";


        ThreadPoolManager.getInstance().getSysLogThreadPool().execute(new SecLogProduceThread(log1));
        ThreadPoolManager.getInstance().getSysLogThreadPool().execute(new SecLogProduceThread(log2));
        ThreadPoolManager.getInstance().getSysLogThreadPool().execute(new SecLogProduceThread(log3));
        //消费
        ThreadPoolManager.getInstance().getSecLogThreadPool().execute(new SecLogConsumeThread());
        TimeUnit.SECONDS.sleep(5);

        ThreadPoolManager.getInstance().getSysLogThreadPool().execute(new SecLogProduceThread(log4));
        ThreadPoolManager.getInstance().getSysLogThreadPool().execute(new SecLogProduceThread(log5));
    }
}