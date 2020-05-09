package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wxz.confsysdomain.paper.Solicite;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/6 23:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperManagCenterVo {

    private List<UserVo> professorList;

    private List<PaperVo> paperVoList;

    private Solicite solicite;

    private List<PaperViewResultVo> viewResultVoList;

}
