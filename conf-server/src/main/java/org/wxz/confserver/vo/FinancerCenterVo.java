package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/9 15:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancerCenterVo {

    private List<FinancerVo> hasPayList;

    private List<FinancerVo> nonPayList;

    private PayCategory payCategory;

}
