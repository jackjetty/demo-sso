package com.siemens.csde.sso.controller;

import com.google.gson.Gson;
import com.siemens.csde.sso.pojo.vo.UserVo;
import com.siemens.csde.sso.util.SpringContextUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private Gson gson;

     //@PreAuthorize("#oauth2.hasScope('simicase.test')")
      //@PreAuthorize(value="isAuthenticated()")
     //@PreAuthorize("principal.username.equals(#username)")
    //authentication.isClientOnly()
      @PreAuthorize("hasAnyRole('ROLE_USER1','ROLE_ADMIN1')")
     //@PreAuthorize("#oauth2.hasAnyRole('role.test2')")
    @GetMapping(value = "user/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo getUser(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="userId",required = false) String userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
         //authentication.getAuthorities();
         OAuth2AuthenticationDetails userDetails= (OAuth2AuthenticationDetails)authentication.getDetails();
        log.info("json:{}",gson.toJson(authentication)) ;
        //log.info("auth:{}", SpringContextUtil.getBean("oauth2").getClass());
          //org.springframework.security.access.expression.SecurityExpressionRoot
          //org.springframework.security.access.expression.SecurityExpressionOperations
         authentication.getAuthorities();
         /*for (GrantedAuthority ga : authentication.getAuthorities()) {
             if (needPermission.equals(ga.getAuthority())) {
                 return;
             }
         }*/
         //System.out.println("User has authorities: " + userDetails.getAuthorities());
        log.info("pri:{}",authentication.getPrincipal());
        return UserVo.builder().id(userId).name(currentPrincipalName).build();
    }


}