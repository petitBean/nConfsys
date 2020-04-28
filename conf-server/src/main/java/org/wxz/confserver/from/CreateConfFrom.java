package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/23 13:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateConfFrom {


    private String confName;

    private String  keyWords;

    private String confTopic;

    private String[] tags;

    private String address;

    private String isOnline;

    private Date[] dates;

    private String confIntroduce;

    private String userName;


}
