package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

/**
 * 返回给管理员的
 * @Author xingze Wang
 * @create 2020/4/29 0:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationVo {

    private String applicationId;

    private String userName;

    private String confId;

    private String statusStr;

    private String applyDataStr;

    private String dealDateStr;

}
