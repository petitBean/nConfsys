package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/9 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancerVo {

    private String userName;

    private String name;

    private String email;

    private String phone;

    private double amount;

}
