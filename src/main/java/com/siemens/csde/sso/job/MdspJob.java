package com.siemens.csde.sso.job;

import com.google.gson.Gson;
import com.siemens.csde.sso.constant.EnvConstant;
import com.siemens.csde.sso.feign.OAuthFeignClient;
import com.siemens.csde.sso.feign.TimeSeriesFeignClient;
import com.siemens.csde.sso.pojo.bean.TokenBean;
import com.siemens.csde.sso.util.AuthUtil;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class MdspJob {

    @Autowired
    private Gson gson;
    @Autowired
    private OAuthFeignClient oAuthFeignClient;

    @Autowired
    private TimeSeriesFeignClient timeSeriesFeignClient;

    @Scheduled(fixedRate = 10000,initialDelay = 4*1000)
    public void getIotSeries(){

        EnvConstant.iotTimeSeriesQos.parallelStream().forEach(iotTimeSeriesQo -> {
            Object obj;
            try{
                obj=timeSeriesFeignClient.getIotTimeSeries(iotTimeSeriesQo.getAssetId(),iotTimeSeriesQo.getApsectName(),iotTimeSeriesQo.getFrom().toString(),iotTimeSeriesQo.getTo().toString(),null,null,EnvConstant.TOKEN);
                log.info("body:{}", obj.toString());
            }catch (Exception ex){
                log.error("iot error:",ex);
            }
        });

    }


    @Scheduled(fixedRate = 1000*60*29)
    public void refreshClientToken(){

        String auth= AuthUtil.getBasicAuth(EnvConstant.TENANT_CLIENT_ID,EnvConstant.TENANT_CLIENT_SECRET);
        TokenBean tokenBean=oAuthFeignClient.getToken(auth, OAuthFeignClient.GRANT_TYPE);
        EnvConstant.TOKEN=tokenBean.getBearerToken();
        log.info("token:{}",EnvConstant.TOKEN);

    }
}