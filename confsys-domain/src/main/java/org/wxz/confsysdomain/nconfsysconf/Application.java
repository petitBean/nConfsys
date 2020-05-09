package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/29 0:17
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Application {

    @Id
    private String applicationId;

    private String userName;

    private String confId;

    private int status;

    private String reason;

    private Date applyData;

    private Date dealDate;

}
