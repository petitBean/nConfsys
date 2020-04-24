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

    ROLE_USER(1,"ROLE_USER"),
    ROLE_PROGESSOR(2,"ROLE_PROGESSOR"),
    ROLE_PAPER_MANAGER(3,"ROLE_PAPER_MANAGER"),
    ROLE_INFO_MANAGER(4,"ROLE_INFO_MANAGER"),
    ROLE_FINANCER(5,"ROLE_FINANCER"),
    ROLE_SECRETARY(6,"ROLE_SECRETARY"),


    ;
    private int code;

    private String roleName;
}
