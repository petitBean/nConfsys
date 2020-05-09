package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteUserInfoFrom {

    private String username;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String gender;

    private String confirmCode;

}
