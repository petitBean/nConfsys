package org.wxz.confsysdomain.paper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/6 16:04
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicite {

    @Id
    private String soliciteId;

    private String confId;

    private Date startDate;

    private  Date endDate;

    private String demand;

    private int status;

    private String viewDemand;

}
