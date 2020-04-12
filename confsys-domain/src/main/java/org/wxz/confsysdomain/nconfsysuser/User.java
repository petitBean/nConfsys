package org.wxz.confsysdomain.nconfsysuser;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/12 0:31
 */
@Data
@Entity
public class User {

    @GeneratedValue
    private Integer id;

    @Id
    private String userId;

    private String userName;

    private String password;

    private String salt;

    private String email;

    private String phone;

    private int sex;

    private int age;

    private int status;

    private Date createDate;

    private Date lastLoginTime;

}
