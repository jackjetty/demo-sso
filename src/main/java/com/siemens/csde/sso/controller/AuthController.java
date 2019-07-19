package com.siemens.csde.sso.controller;

import com.google.gson.Gson;
import com.siemens.csde.sso.constant.EnvConstant;
import com.siemens.csde.sso.feign.OAuthFeignClient;
import com.siemens.csde.sso.pojo.bean.TokenBean;
import com.siemens.csde.sso.pojo.vo.UserVo;
import com.siemens.csde.sso.util.AuthUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class AuthController {

    @Autowired
    private Gson gson;

    @Autowired
    private OAuthFeignClient oAuthFeignClient;

    @GetMapping(value = "/client_token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TokenBean getClientToken(HttpServletRequest request, HttpServletResponse response){
        String auth= AuthUtil.getBasicAuth(EnvConstant.TENANT_CLIENT_ID,EnvConstant.TENANT_CLIENT_SECRET);
        TokenBean tokenBean=oAuthFeignClient.getToken(auth,OAuthFeignClient.GRANT_TYPE);
        return tokenBean;
    }

}