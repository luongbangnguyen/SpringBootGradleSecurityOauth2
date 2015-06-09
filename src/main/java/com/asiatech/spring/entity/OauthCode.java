package com.asiatech.spring.entity;

import javax.persistence.*;

/**
 * Created by banngnguyenluong on 08/06/2015.
 */
@Entity
@Table(name = "oauth_code")
public class OauthCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;

    @Lob
    private String authentication;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
