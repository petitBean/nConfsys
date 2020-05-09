package org.wxz.confserver.service.impl;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxz.confserver.from.CommentFrom;
import org.wxz.confsysdomain.paper.Paper;
import org.wxz.nconfsyscommon.enums.PaperStatusEnum;

import javax.xml.stream.events.Comment;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/5/7 0:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PaperServiceImplTest {

    @Autowired
    private PaperServiceImpl paperService;

    @Test
    void saveOne() {
    }

    @Test
    void findListByConfId() {
    }

    @Test
    void updateOne() {
    }

    @Test
    void createOne() throws Exception{
        Paper paper=paperService.createOne("13720131232","nn.pdf","0001","topic");
        return;
    }

    @Test
    void getPaperVolistByConfid() {
    }

    @Test
    void getPaperViewRsultList() {
    }

    @Test
    void indListByProfessorUserNameAndConfIdAndStatus() throws Exception{
        List<Paper> paperList=paperService.findListByProfessorUserNameAndConfIdAndStatus("13720131234","1588971220733419968", PaperStatusEnum.PAPER_STATUS_ENUM_NEW.getCode());
        return;
    }

    @Test
    void commentPaper() throws Exception{
        CommentFrom commentFrom=new CommentFrom();
        commentFrom.setComment("哈哈哈哈");
        commentFrom.setScore(23.6);
        commentFrom.setUserName("13720131233");
        commentFrom.setPaperId("1588983128226675954");
        paperService.commentPaper(commentFrom);
    }

}