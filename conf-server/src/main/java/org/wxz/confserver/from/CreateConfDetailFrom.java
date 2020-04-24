package org.wxz.confserver.from;

import lombok.Data;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/25 0:34
 */
@Data
public class CreateConfDetailFrom {

   private String confSession;

    private String confSubject;

    private String bigPosterUrl;

    private String  contect;

    private int  peopleNum;

    private Date[] paperCollectionDate;

    private Date[] payDate;

    private String groupIntroduce;

    private String confId;
}
