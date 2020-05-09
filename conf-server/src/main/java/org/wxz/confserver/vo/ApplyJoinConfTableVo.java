package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;
import org.wxz.confsysdomain.paper.Solicite;

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

    private String startTimeStr;

    private String confDetailId;

    private String confIntroduce;

    private String applicationStatus;

    private String confStatusStr;

    private int status;

    private Solicite solicite;

    private PayCategory payCategory;

}

