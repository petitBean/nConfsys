package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCategoryFrom {

    private double amount;

    private String confId;

    private String payDemand;

    private Date[] dates;


}
