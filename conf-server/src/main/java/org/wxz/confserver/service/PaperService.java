package org.wxz.confserver.service;

import org.wxz.confserver.from.CommentFrom;
import org.wxz.confserver.vo.PaperVo;
import org.wxz.confsysdomain.paper.Paper;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/6 15:37
 */
public interface PaperService {

    Paper saveOne (Paper paper) throws Exception;

    List<Paper> findListByConfId(String confId) throws Exception;

    Paper updateOne(Paper paper) throws Exception;

    Paper createOne(String authorUserName,String fileName,String confId,String topic) throws Exception;

    List<PaperVo> getPaperVolistByConfid(String confId)throws Exception;

    List<Paper> findListByProfessorUserNameAndConfIdAndStatus(String professorUserName,String confId,int status)throws Exception;

    Paper commentPaper(CommentFrom from)throws Exception;

}
