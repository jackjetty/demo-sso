package com.siemens.csde.sso.util;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

public class AuthUtil {

    public static String getBasicAuth(String clientId, String secret) {
        String auth = clientId + ":" + secret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }


}