package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/26 22:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfManagTableVo {

    private String confDetailId;

    private String confId;

    private String confName;

    private String confIntroduce;

    private String confTopic;

    private String startTimeStr;

    private String endTimeStr;

    private String statusStr;

    //
    private String roleName;

    private String description;


}
