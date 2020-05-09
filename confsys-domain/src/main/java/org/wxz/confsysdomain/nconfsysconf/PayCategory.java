package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/9 6:18
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PayCategory {


    @Id
    private String id;

    private String payDemand;

    private int status;

    private String confId;

    private Date startDate;

    private double amount;

    private Date endDate;

    private Date createTime;


}
