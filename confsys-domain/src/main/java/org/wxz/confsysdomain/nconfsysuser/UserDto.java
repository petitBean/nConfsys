package org.wxz.confsysdomain.nconfsysuser;

import lombok.Data;

/**
 * @Author xingze Wang
 * @create 2020/4/17 7:16
 */
@Data
public class UserDto {

    private String userId;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private int sex;

    private int age;


}
