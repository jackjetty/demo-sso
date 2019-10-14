package com.siemens.csde.sso.controller;

import com.google.gson.Gson;
import com.siemens.csde.sso.pojo.vo.UserVo;
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
public class UserController {

    @Autowired
    private Gson gson;

    @PreAuthorize("#oauth2.hasScope('simicasmp.user')")
    @GetMapping(value = "user/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo getUser(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="userId",required = false) String userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("json:{}",gson.toJson(authentication)) ;
       // System.out.println("User has authorities: " + userDetails.getAuthorities());
        log.info("pri:{}",authentication.getPrincipal());
        return UserVo.builder().id(userId).name(currentPrincipalName).build();
    }


}