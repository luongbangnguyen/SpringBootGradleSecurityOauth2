package com.asiatech.spring.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by bangnnguyenluong on 08/06/2015.
 */
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String appId;

    private String resourceIds;

    private String appSecret;

    private String scope;

    private String grantTypes;

    private String redirectUrl;

    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(length = 4096)
    private String additionalInformation;

}
