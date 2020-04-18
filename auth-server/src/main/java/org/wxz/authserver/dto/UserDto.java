package org.wxz.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @Author xingze Wang
 * @create 2020/4/16 14:08
 */
@Data
@AllArgsConstructor
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    private Set<String> authorites;

}
