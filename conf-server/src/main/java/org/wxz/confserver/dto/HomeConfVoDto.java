package org.wxz.confserver.dto;

import lombok.Data;
import org.wxz.confserver.vo.HomeConfVo;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/25 17:41
 */
@Data
public class HomeConfVoDto {
    List<HomeConfVo> confVoList;

    int count=0;
}
