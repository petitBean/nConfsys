package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/4/28 23:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJoinConfTableVo {

    private String confId;

    private String confName;

    private String status;

    private String startTimeStr;

    private String confDetailId;

}

