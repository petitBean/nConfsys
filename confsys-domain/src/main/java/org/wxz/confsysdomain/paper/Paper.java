package org.wxz.confsysdomain.paper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/6 15:08
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paper {

    @Id
    private String paperId;

    private String confId;

    private String paperTopic;

    private String authorUserName;

    private String professorUserName;

    private String professorUserNameSecond;

    private int status;

    private int firstStatus;

    private int secondStatus;

    private double firstScore=0;

    private double secondScore=0;

    private double score=0;

    private Date createTime;

    private String comment;

    private String firstComment;

    private String secondComment;

    private String fileName;

}
