package com.siemens.csde.sso.controller;

import com.google.gson.Gson;
import com.siemens.csde.sso.constant.EnvConstant;
import com.siemens.csde.sso.feign.OAuthFeignClient;
import com.siemens.csde.sso.pojo.bean.TokenBean;
import com.siemens.csde.sso.pojo.qo.CacheQo;
import com.siemens.csde.sso.util.AuthUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CacheController{
    @Autowired
    private Gson gson;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/cache/{key}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getKey(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="key",required = true) String key){

        Object obj=redisTemplate.opsForValue().get(key);
        obj=obj==null?"":obj;
        return obj;

    }

    @GetMapping(value = "/cache/{key}/{value}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean setValue(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="key",required = true) String key,@PathVariable(value="value",required = true) String value){

        redisTemplate.opsForValue().set(key, value);
        return true;

    }

    @PostMapping(value = "/cache/{key}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean addKey(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="key",required = true) String key,
            @RequestBody CacheQo cacheQo){

        redisTemplate.opsForValue().set(key, cacheQo);
        return true;

    }
}