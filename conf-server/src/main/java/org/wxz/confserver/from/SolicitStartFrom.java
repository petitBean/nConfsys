package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/8 1:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitStartFrom {

    private String confId;

    private String demand;

    private Date[] dates;

    private String viewDemand;

}
