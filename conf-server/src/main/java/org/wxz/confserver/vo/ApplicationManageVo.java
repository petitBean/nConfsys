package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/4 2:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationManageVo {

    private String userName;

    private String name;

    private String email;

    private String phone;

    private String statusStr;

    private String applicationId;

    private String reason;

}
