package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/29 1:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resource {

    @Id
    private String resourceId;

    private String uploadUserName;

    private String confId;

    private int type;

    private String OriginName;

    private String storeName;

    private Date uploadDate;

    private int downloadTimes=0;

    private String note;
}
