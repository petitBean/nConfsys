package org.wxz.authserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/16 23:09
 */
@Entity
@Data
public class OauthClientDetails {

    @Id
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private int accessTokenValidity;

    private int refreshTokenValidity;

    private String addtionalInfomation;

    private String autoapprove;

    private Date createTime;

    private Date updateTime;

}
