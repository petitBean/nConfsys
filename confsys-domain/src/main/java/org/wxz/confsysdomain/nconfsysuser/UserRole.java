package org.wxz.confsysdomain.nconfsysuser;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:05
 */
@Entity
@Data
public class UserRole {

    @GeneratedValue
    @Id
    private Integer id;

    private String userId;

    private String roleId;

}
