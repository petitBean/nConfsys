package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:24
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayOrder {

    @Id
    private String payOrderId;

    private String userName;

    private String confId;

    private Date createDate;

    private String payDate;

    private int status;

    private Double amount;

}
