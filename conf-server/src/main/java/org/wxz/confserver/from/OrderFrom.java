package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/9 16:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFrom {

    private String userName;

    private String confId;

    private double amount;

}
