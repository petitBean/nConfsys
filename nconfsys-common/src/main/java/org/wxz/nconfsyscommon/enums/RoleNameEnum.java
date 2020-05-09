package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:35
 */
@AllArgsConstructor
@Getter
public enum RoleNameEnum {

    ROLE_USER(1,"ROLE_USER","普通用户"),
    ROLE_PROGESSOR(2,"ROLE_PROGESSOR","专家"),
    ROLE_PAPER_MANAGER(3,"ROLE_PAPER_MANAGER","论文管理员"),
    ROLE_INFO_MANAGER(4,"ROLE_INFO_MANAGER","会议信息管理员"),
    ROLE_FINANCER(5,"ROLE_FINANCER","财务管理"),
    ROLE_SECRETARY(6,"ROLE_SECRETARY","秘书组"),

    ;
    private int code;

    private String roleName;

    private String message;

    public static RoleNameEnum getByRoleName(String roleName){
        for (RoleNameEnum roleNameEnum:RoleNameEnum.values()){
            if (roleNameEnum.getRoleName().equals(roleName)){
                return roleNameEnum;
            }
        }
        return null;
    }

}
