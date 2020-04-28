package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/26 23:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfManageCenterVo {

    /*
    会议管理中心管理的会议列表
     */
    private List<ConfManagTableVo> tableVoList;

    private String code="bbbbb";

    //加上通知公告列表

}
