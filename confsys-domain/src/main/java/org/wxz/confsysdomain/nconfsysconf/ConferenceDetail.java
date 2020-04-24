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

    private String confSession;

    private String confSubject;

    private String groupIntroduce;

    private String bigPosterUrl;

    private String littlePosterUrl;

    private String confPlanPaper;

    private String contect;

    private int peopleNum=0;

    private Date paperCollectionStart;

    private Date payStartTime;

    private Date payEndTime;

    private Date paperCollectionEnd;

    private Date creteTime=new Date();

    private Date updateTime;

}
