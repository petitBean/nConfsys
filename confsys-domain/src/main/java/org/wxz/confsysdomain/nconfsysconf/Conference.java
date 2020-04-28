package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/22 17:49
 */
@Data
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conference {

    @GeneratedValue
    private int id;

    @Id
    private String confId;

    /**
     * 名称
     */
    private String confName;

    private String  keyWords;

    private String confIntroduce;

    /**
     * 主题
     */
    private String confTopic;

    private String confDetailId;

    private String confTagId;

    private Date startTime;

    private Date endTime;

    private Date createTime=new Date();

    private Date updateTime;

    private int focusedOn;

    private int isOnline;

    private int status;

}
