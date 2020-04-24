package org.wxz.authserver.enums;

import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/22 16:15
 */
@Getter
public enum RoleEnum {
    ROLE_USER("ROLE_USER"),
    ;
    private String roleName;

    RoleEnum(String roleName){
        this.roleName=roleName;
    }
}
