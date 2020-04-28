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

    private String startTimeStr;

    private String endTimeStr;

    private int focusedOn;

    private String isOnlineStr;

    private String statusStr;

    private String confIntroduce;

    private String createTimeStr;

    //
    private String payDemanded;

    private String paperDemanded;


    private String confSession;

    private String confSubject;

    private String groupIntroduce;

    private String contect;

    private String paperCollectionStartStr;

    private String paperCollectionEndStr;

    private String payStartTimeStr;

    private String payEndTimeStr;

    private String confOrgnizeIntroduce;


}
