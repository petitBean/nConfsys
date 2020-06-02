package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/24 22:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfListVo {

    private List<HomeConfVo> conferenceList;

    //private List<HomeConfVo> commandedList;

    //private List<String> tagList;

    int countAll=0;
}
