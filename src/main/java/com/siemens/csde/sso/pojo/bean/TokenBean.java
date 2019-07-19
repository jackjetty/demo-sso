package com.siemens.csde.sso.pojo.bean;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenBean {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private Long expiresIn;

    private String scope;

    @SerializedName("jti")
    private String jti;

    public String getBearerToken() {
        return "Bearer " + accessToken;
    }
}