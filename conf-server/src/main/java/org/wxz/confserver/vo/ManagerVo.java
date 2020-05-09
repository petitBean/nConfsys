package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作人员列表
 * @Author xingze Wang
 * @create 2020/5/5 20:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerVo {

    private String userName;

    private String name;

    private String email;

    private String phone;

    private String statusStr;

    private String roleMessage;

}
