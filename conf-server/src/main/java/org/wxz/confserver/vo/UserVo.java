package org.wxz.confserver.vo;

/**
 * @Author xingze Wang
 * @create 2020/5/6 22:55
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户视图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private String userName;

    private String name;

    private String email;

    private String phone;

    private String statusStr;

    private String roleMessage;

}
