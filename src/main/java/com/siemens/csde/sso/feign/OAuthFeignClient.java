package com.siemens.csde.sso.feign;

import com.siemens.csde.sso.pojo.bean.TokenBean;
import java.net.URI;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="oauth-client",url="https://dicsdev.piam.cn1.mindsphere-in.cn/uaa")
public interface OAuthFeignClient{

    String  GRANT_TYPE = "client_credentials";

    @RequestMapping(value = "/oauth/token", method = RequestMethod.GET)
    TokenBean getToken( @RequestHeader("Authorization") String token,
            @RequestParam("grant_type") String grantType);


}

