package com.siemens.csde.sso.feign;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "iot-client",url = "https://gateway.cn1.mindsphere-in.cn/api/iottimeseries/v3")
public interface TimeSeriesFeignClient {

    @RequestMapping( value = "/timeseries/{entity}/{propertysetname}",  method = GET  )
    void getIotTimeSeries(
            @PathVariable("entity") String entity,
            @PathVariable("propertysetname") String propertySetName,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "limit", defaultValue = "1000") Integer limit,
            @RequestParam("select") String select,
            @RequestHeader("Authorization") String token);


}