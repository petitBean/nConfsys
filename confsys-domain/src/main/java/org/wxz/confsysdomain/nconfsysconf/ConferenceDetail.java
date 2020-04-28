package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/22 17:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
public class ConferenceDetail {

    @GeneratedValue
    private int id;

    @Id
    private String confDetailId;

    /**
     * 会期
     */
    private String confSession;

    /**
     * 议题
     */
    private String confSubject;

    /**
     * 主要组织介绍
     */
    private String groupIntroduce;

    /**
     * 主办方
     */
    private String confOrgnizeIntroduce;

    private String bigPosterUrl;

    private String littlePosterUrl;

    private String confPlanPaper;

    private String contect;

    private int peopleNum=0;

    private Date paperCollectionStart;

    private Date payStartTime;

    private Date payEndTime;

    /**
     * 会费要求/说明
     */
    private String payDemanded;

    /**
     * 征文要求和说明
     */
    private String paperDemanded;

    private Date paperCollectionEnd;

    private Date creteTime=new Date();

    private Date updateTime;

}
