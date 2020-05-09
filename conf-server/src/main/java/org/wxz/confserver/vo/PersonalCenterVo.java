package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/28 23:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalCenterVo {

 private int code;

 List<ApplyJoinConfTableVo> joinedConference;

 List<MyManagConfTableVo> myManagConfTableVoList;

}
