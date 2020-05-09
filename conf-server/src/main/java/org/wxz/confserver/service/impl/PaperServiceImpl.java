package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.CommentFrom;
import org.wxz.confserver.repository.PaperRepository;
import org.wxz.confserver.service.PaperService;
import org.wxz.confserver.vo.PaperViewResultVo;
import org.wxz.confserver.vo.PaperVo;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.confsysdomain.paper.Paper;
import org.wxz.confsysdomain.relation.ConferenceUer;
import org.wxz.nconfsyscommon.enums.PaperStatusEnum;
import org.wxz.nconfsyscommon.enums.RoleNameEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.DateUtil;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import java.util.*;

/**
 * @Author xingze Wang
 * @create 2020/5/6 15:40
 */
@Slf4j
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ConferenceUserServiceImpl conferenceUserService;

    @Override
    public Paper saveOne(Paper paper) throws Exception {
        return paperRepository.save(paper);
    }

    @Override
    public List<Paper> findListByConfId(String confId) throws Exception {
        return null;
    }

    @Override
    public Paper updateOne(Paper paper) throws Exception {
        return saveOne(paper);
    }

    @Override
    public Paper createOne(String authorUserName, String fileName, String confId, String topic) throws Exception{
       if (authorUserName==null||fileName==null||confId==null||topic==null){
           log.error("创建pape-错误-参数为空");
           throw new ConfException("创建pape-错误-参数为空");
       }
        Paper paperFounded=null;
       try {
           paperFounded=paperRepository.findByConfIdAndAuthorUserName(confId,authorUserName);
       }catch (Exception e){
           log.info("用户上传论文-出错");
           throw new ConfException("系统异常！");
       }
       if (paperFounded!=null){
           log.info("用户上传论文-论文已经存在");
           throw new ConfException("您已经上传过论文！");
       }
        Paper paper=new Paper();
        paper.setPaperId(KeyUtil.getUniqueKey());
        paper.setAuthorUserName(authorUserName);
        paper.setFileName(fileName);
        paper.setConfId(confId);
        paper.setPaperTopic(topic);
        paper.setCreateTime(new Date());
        //TODO 如果开始评阅要分配专家
        paper.setStatus(PaperStatusEnum.PAPER_STATUS_ENUM_NEW.getCode());
        return saveOne(paper);
    }

    /*查询论文列表
     */
    @Override
    public List<PaperVo> getPaperVolistByConfid(String confId) throws Exception {
        if (confId==null){
            log.error("查询PaperVo-失败-confId");
            return null;
        }
        List<Paper> paperList=paperRepository.findAllByConfId(confId);
        if (paperList==null){
            log.error("查询PaperVo-失败-paperList");
            return null;
        }
        Map<String,Paper> paperMap=new HashMap<>();
        List<String> userNameList=new LinkedList<>();
        for(Paper paper:paperList){
            paperMap.put(paper.getAuthorUserName(),paper);
            userNameList.add(paper.getAuthorUserName());
        }
        List<User> userList=userService.findListByUserNameIn(userNameList);
        if (userList==null){
            log.error("查询PaperVo-失败-userList");
            return null;
        }
        List<PaperVo> voList=new LinkedList<>();
        for (User user:userList){
            String username=user.getUserName();
            Paper paper=paperMap.get(username);
            PaperVo paperVo=new PaperVo();
            BeanUtils.copyProperties(paper,paperVo);
            paperVo.setPaperAuthor(user.getName());
            paperVo.setViewStatus(PaperStatusEnum.getByCode(paper.getStatus()).getMessage());
            voList.add(paperVo);
        }
        return voList;
    }

    @Override
    public List<Paper> findListByProfessorUserNameAndConfIdAndStatus(String professorUserName, String confId,int status) throws Exception {
        List<Paper> paperListFirst=paperRepository.findAllByConfIdAndProfessorUserNameAndFirstStatus(confId,professorUserName,status);
        paperListFirst.addAll(paperRepository.findAllByConfIdAndProfessorUserNameSecondAndSecondStatus(confId,professorUserName,status));
        for(Paper paper:paperListFirst){
            try {
                paper.setProfessorUserName(DateUtil.dateMinuteToStr(paper.getCreateTime()));
            }catch (Exception e){
             log.warn("获取paper-时间转换错误");
            }
        }
        return paperListFirst;
    }

    @Override
    public Paper commentPaper(CommentFrom from) throws Exception {
        Paper paper=null;
        try {
            paper=paperRepository.findByPaperId(from.getPaperId());
        }catch (Exception e){
            log.error("用户评阅论文-失败-查询paper异常 e={}",e.getMessage()+e.getCause());
            throw  new ConfException("评阅失败");
        }
        //判断自己是第几个专家
        if (paper.getProfessorUserName()!=null&&paper.getProfessorUserName().equals(from.getUserName())){
           paper.setFirstComment(from.getComment());
           paper.setFirstScore(from.getScore());
           paper.setFirstStatus(PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
           //判断第二个评论了没
            if (paper.getSecondStatus()==PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode()){
                paper.setScore((paper.getFirstScore()+paper.getSecondScore())/2.0);
                paper.setStatus(PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
            }else {
                paper.setStatus(PaperStatusEnum.PAPER_STATUS_ENUM_VIEW.getCode());
            }
        }else if (paper.getProfessorUserNameSecond()!=null&&paper.getProfessorUserNameSecond().equals(from.getUserName())){
            paper.setSecondComment(from.getComment());
            paper.setSecondScore(from.getScore());
            paper.setSecondStatus(PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
            if (paper.getFirstStatus()==PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode()){
                paper.setStatus(PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
                paper.setScore((paper.getFirstScore()+from.getScore())/2.0);
            }
        }
        //设置评论
        paper.setComment(paper.getComment()+'\n'+"评语："+'\n'+from.getComment());

        try {
            paperRepository.save(paper);
        }
        catch (Exception e){
            log.error("用户评论paper-错误-e={}",e.getMessage()+e.getCause());
            throw new ConfException("评论失败");
        }
        log.info("用户评论paper={}",paper);
        return paper;
    }

    public List<Paper> getViewdList(String confId,String userName){
        List<Paper> first=paperRepository.findAllByConfIdAndProfessorUserNameAndFirstStatus(confId,userName,PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
        List<Paper> second=paperRepository.findAllByConfIdAndProfessorUserNameSecondAndSecondStatus(confId,userName,PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
        List<Paper> paperList=new LinkedList<>();
        if (first!=null){
            for (Paper paper:first){
                paper.setScore(paper.getFirstScore());
                paper.setComment(paper.getFirstComment());
            }
            paperList.addAll(first);
        }
        if (second!=null){
            for (Paper paper:second){
                paper.setScore(paper.getSecondScore());
                paper.setComment(paper.getSecondComment());
            }
            paperList.addAll(second);
        }

        for(Paper paper:paperList){
            try {
                paper.setProfessorUserName(DateUtil.dateMinuteToStr(paper.getCreateTime()));
            }catch (Exception e){
                log.warn("获取paper-时间转换错误");
            }
        }

        log.info("查询用户已经评阅论文！re={}",paperList);
        return paperList;
    }


    /**
     * 评阅结果列表
     * @param confId
     * @return
     */
    public List<PaperViewResultVo> getPaperViewRsultList(String confId){
        if (confId==null){
            log.error("查询PaperVo-失败-confId");
            return null;
        }
        List<Paper> paperList=paperRepository.findAllByConfIdAndStatus(confId,PaperStatusEnum.PAPER_STATUS_ENUM_VIEWED.getCode());
        if (paperList==null){
            log.error("查询PaperVo-失败-paperList");
            return null;
        }
        Map<String,String> paperMap=new HashMap<>();
        List<String> userNameList=new LinkedList<>();
        for(Paper paper:paperList){
            userNameList.add(paper.getProfessorUserName());
        }
        List<User> userList=userService.findListByUserNameIn(userNameList);
        if (userList==null){
            log.error("查询PaperVo-失败-userList");
            return null;
        }
        List<PaperViewResultVo> voList=new LinkedList<>();
        Map<String,User> userMap=new HashMap<>();
        for (User user:userList){
            userMap.put(user.getUserName(),user);
        }
        for (Paper paper:paperList){
            PaperViewResultVo viewResultVo=new PaperViewResultVo();
            BeanUtils.copyProperties(paper,viewResultVo);
            viewResultVo.setName(userMap.get(paper.getProfessorUserName()).getName());
            voList.add(viewResultVo);
        }
        return voList;
    }

    public void distributeProfessor(String confId){
        //查询所有未评阅的论文
        List<Paper> paperList=paperRepository.findAllByConfIdAndStatus(confId,PaperStatusEnum.PAPER_STATUS_ENUM_NEW.getCode());
        //查询所有专家
        List<ConferenceUer> conferenceUerList=conferenceUserService.findAllByConfIdAndRoleName(confId, RoleNameEnum.ROLE_PROGESSOR.getRoleName());
        if (paperList==null||conferenceUerList==null){
            log.info("分配-1");
            return;
        }
        List<String> userIdList=new LinkedList<>();
        for (ConferenceUer conferenceUer:conferenceUerList){
            userIdList.add(conferenceUer.getUserName());
        }
        String[] a=new String[userIdList.size()];
        int j=0;
        for (String string:userIdList){
            a[j++]=string;
        }
        int i=0;
        for (Paper paper:paperList){
           if (i<a.length){
               paper.setProfessorUserName(a[i]);
               i++;
           }
           else{
               i=0;
               paper.setProfessorUserName(a[i]);
               i++;
           }
           if (i<a.length){
               paper.setProfessorUserNameSecond(a[i]);
               i++;
           }else {
               i=0;
               paper.setProfessorUserNameSecond(a[i]);
               i++;
           }

        }
        paperRepository.saveAll(paperList);

    }

}
