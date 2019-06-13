package com.siemens.csde.sso.config.security;
import com.siemens.csde.sso.config.security.store.MyJwtAccessTokenConverter;
import com.siemens.csde.sso.config.security.store.MyTokenServices;
import com.siemens.csde.sso.handler.AuthExceptionEntryPoint;
import com.siemens.csde.sso.handler.CustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import com.siemens.csde.sso.config.security.store.MyJwtTokenStore;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.transaction.annotation.Transactional;
@EnableResourceServer
@Configuration
@Slf4j
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Bean
    @Qualifier("authorizationHeaderRequestMatcher")
    public RequestMatcher authorizationHeaderRequestMatcher() {
        return new RequestHeaderRequestMatcher("Authorization");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
     /*  http
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatcher(authorizationHeaderRequestMatcher());*/

          /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                 ;*/
                //.authorizeRequests().anyRequest().
                 //.permitAll();

        http.authorizeRequests().anyRequest().authenticated()
                .and().anonymous().disable();

        //.access("#oauth2.hasScope('read')");


    }

  /*  @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId("resource1");
         if (Objects.nonNull(remoteTokenServices)) {
            resources.tokenServices(remoteTokenServices);
        }
    }
*/
    /*@Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenService.setClientId("testclientid");
        tokenService.setClientSecret("1234");
        return tokenService;
    }*/

/*    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(false);
        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new MyJwtTokenStore(accessTokenConverter());
    }
    @Bean
    public MyJwtAccessTokenConverter accessTokenConverter() {
        MyJwtAccessTokenConverter converter = new MyJwtAccessTokenConverter();
        //PublicKey publicKey = "123";
        //String strPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());`
       //String verifierKey = String.format("-----BEGIN PUBLIC KEY-----\n%s\n-----END PUBLIC KEY-----", "123");
        //converter.setVerifierKey(verifierKey);/
        converter.setVerifierKey("123");
        //converter.setAccessTokenConverter(new MethodSecurityConfig());
        return converter;
    }*/



    @Bean
    public MyJwtAccessTokenConverter accessTokenConverter(){
        MyJwtAccessTokenConverter converter = new MyJwtAccessTokenConverter();
        converter.setAccessTokenConverter(new DefaultAccessTokenConverter());
        //converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new MyJwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(null);
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices()  ;
        defaultTokenServices.setTokenStore(tokenStore());
        resources.tokenServices(defaultTokenServices);
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
               // .accessDeniedHandler(new CustomAccessDeniedHandler());
        //defaultTokenServices.setSupportRefreshToken(false);
    }
}