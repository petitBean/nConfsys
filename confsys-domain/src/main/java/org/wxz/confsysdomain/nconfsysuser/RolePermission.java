package org.wxz.confsysdomain.nconfsysuser;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xingze Wang
 * @create 2020/4/12 1:07
 */
@Data
@Entity
public class RolePermission {

    @GeneratedValue
    @Id
    private Integer id;

    private String roleId;

    private String permissionId;

}
