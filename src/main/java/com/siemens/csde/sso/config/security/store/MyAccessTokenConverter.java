package com.siemens.csde.sso.config.security.store;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
@Slf4j
public class MyAccessTokenConverter implements AccessTokenConverter {
    private UserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
    private boolean includeGrantType;

    public MyAccessTokenConverter() {
    }

    public void setUserTokenConverter(UserAuthenticationConverter userTokenConverter) {
        this.userTokenConverter = userTokenConverter;
    }

    public void setIncludeGrantType(boolean includeGrantType) {
        this.includeGrantType = includeGrantType;
    }

    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> response = new HashMap();
        OAuth2Request clientToken = authentication.getOAuth2Request();
        if (!authentication.isClientOnly()) {
            response.putAll(this.userTokenConverter.convertUserAuthentication(authentication.getUserAuthentication()));
        } else if (clientToken.getAuthorities() != null && !clientToken.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(clientToken.getAuthorities()));
        }

        if (token.getScope() != null) {
            response.put("scope", token.getScope());
        }

        if (token.getAdditionalInformation().containsKey("jti")) {
            response.put("jti", token.getAdditionalInformation().get("jti"));
        }

        if (token.getExpiration() != null) {
            response.put("exp", token.getExpiration().getTime() / 1000L);
        }

        if (this.includeGrantType && authentication.getOAuth2Request().getGrantType() != null) {
            response.put("grant_type", authentication.getOAuth2Request().getGrantType());
        }

        response.putAll(token.getAdditionalInformation());
        response.put("client_id", clientToken.getClientId());
        if (clientToken.getResourceIds() != null && !clientToken.getResourceIds().isEmpty()) {
            response.put("aud", clientToken.getResourceIds());
        }

        return response;
    }

    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(value);
        Map<String, Object> info = new HashMap(map);
        info.remove("exp");
        info.remove("aud");
        info.remove("client_id");
        info.remove("scope");
        if (map.containsKey("exp")) {
            token.setExpiration(new Date((Long)map.get("exp") * 1000L));
        }

        if (map.containsKey("jti")) {
            info.put("jti", map.get("jti"));
        }
        token.setScope(this.extractScope(map));
        token.setAdditionalInformation(info);
        return token;
    }

    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        Map<String, String> parameters = new HashMap();
        Set<String> scope = this.extractScope(map);
        Map<String, Object> info = new HashMap(map);
        info.put("authorities",Lists.newArrayList("ROLE_USER","ROLE_ADMIN"));
        map=info;
        Authentication user = this.userTokenConverter.extractAuthentication(map);
        //log.info("scope:{},user:{}",scope,user);


        String clientId = (String)map.get("client_id");
        parameters.put("client_id", clientId);
        if (this.includeGrantType && map.containsKey("grant_type")) {
            parameters.put("grant_type", (String)map.get("grant_type"));
        }

        Set<String> resourceIds = new LinkedHashSet((Collection)(map.containsKey("aud") ? this.getAudience(map) : Collections.emptySet()));
        Collection<? extends GrantedAuthority> authorities = null;
        if (user == null && map.containsKey("authorities")) {
            String[] roles = (String[])((Collection)map.get("authorities")).toArray(new String[0]);
            authorities = AuthorityUtils.createAuthorityList(roles);
        }
       /*  List<GrantedAuthority> myAuthorities = new ArrayList( );
        SimpleGrantedAuthority simpleGrantedAuthority=new    SimpleGrantedAuthority("role.test1");
        myAuthorities.add(  simpleGrantedAuthority);
        simpleGrantedAuthority=new    SimpleGrantedAuthority("role.test2");
        myAuthorities.add(  simpleGrantedAuthority);
        authorities=myAuthorities; */
        //log.info("authorities:{}",authorities);
        OAuth2Request request = new OAuth2Request(parameters, clientId, authorities, true, scope, resourceIds, (String)null, (Set)null, (Map)null);
        return new OAuth2Authentication(request, user);
    }

    private Collection<String> getAudience(Map<String, ?> map) {
        Object auds = map.get("aud");
        if (auds instanceof Collection) {
            Collection<String> result = (Collection)auds;
            return result;
        } else {
            return Collections.singleton((String)auds);
        }
    }

    private Set<String> extractScope(Map<String, ?> map) {
        Set<String> scope = Collections.emptySet();
        if (map.containsKey("scope")) {
            Object scopeObj = map.get("scope");
            if (String.class.isInstance(scopeObj)) {
                scope = new LinkedHashSet(Arrays.asList(((String)String.class.cast(scopeObj)).split(" ")));
            } else if (Collection.class.isAssignableFrom(scopeObj.getClass())) {
                Collection<String> scopeColl = (Collection)scopeObj;
                scope = new LinkedHashSet(scopeColl);
            }
        }
        scope.add("simicase.test");
        return (Set)scope;
    }
}
