package org.wxz.confserver.vo;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/22 21:08
 */
@Data
public class DetailPageVo {

    private String confId;

    private String confName;

    private String  keyWords;

    private String confTopic;

    private Date startTime;

    private Date endTime;

    private int focusedOn;

    private int isOnlie;

    private int status;

    //
    private String confSession;

    private String confSubject;

    private String groupIntroduce;

    private String contect;

    private Date paperCollectionStart;

    private Date paperCollectionEnd;

    private Date payStartTime;

    private Date payEndTime;


}
