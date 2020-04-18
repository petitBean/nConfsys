package org.wxz.authserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/16 23:19
 */
@Entity
@Data
public class OauthCode {

    @Id
    private String id;

    private Date createTime;

    private String code;

    private String authentication;

}
