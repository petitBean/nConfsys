package org.wxz.confsysdomain.nconfsysuser;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/12 0:54
 */
@Data
@Entity
public class Permission {

    @GeneratedValue
    private Integer id;

    @Id
    private String permissionId;

    private String permissionCode;

    private String permissionName;

    private String url;

    private String description;

    private int status;

    private Date createTime;

    private Date updateTime;


}
