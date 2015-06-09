package com.asiatech.spring.entity;

import javax.persistence.*;

/**
 * Created by root on 08/06/2015.
 */
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "token_id")
    private String tokenId;

    @Lob
    private String token;

    @Lob
    private String authentication;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
