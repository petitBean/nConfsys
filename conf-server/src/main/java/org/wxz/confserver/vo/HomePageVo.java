package org.wxz.confserver.vo;

import lombok.Data;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/24 3:42
 */
@Data
public class HomePageVo {

    private List<HomeConfVo> conferenceList;

    private List<HomeConfVo> commandedList;

    private List<String> tagList;

    int countAll=0;


}
