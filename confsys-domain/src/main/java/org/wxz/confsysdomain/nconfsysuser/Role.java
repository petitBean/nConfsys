package org.wxz.confsysdomain.nconfsysuser;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/12 0:42
 */
@Data
@Entity
public class Role {

    @GeneratedValue
    private Integer id;

    @Id
    private String roleId;

    private String roleName;

    private String description;

    private int status;

    private Date createTime;

    private Date updateTime;

}
