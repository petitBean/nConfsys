package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wxz.confsysdomain.paper.Paper;
import org.wxz.confsysdomain.paper.Solicite;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/8 23:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperViewVo {

    private List<Paper> viewedPaperList;

    private List<Paper> noViewPaperList;

    private Solicite solicit;

}
