package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/3 23:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyManagConfTableVo {

    private String confId;

    private String confName;

    private String confStatusStr;

    private String startTimeStr;

    private String roleName;

}
