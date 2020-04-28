package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/29 2:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateResourceFrom {

    private String uploadUserName;

    private String confId;

    private int type;

    private String OriginName;

    private String storeName;

    private String note;

}
