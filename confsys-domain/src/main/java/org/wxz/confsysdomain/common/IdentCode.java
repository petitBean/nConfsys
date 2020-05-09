package org.wxz.confsysdomain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:09
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentCode {

    @Id
    private String id;

    private String code;

    private String userName;

    private Date createDate;

    private Date updateDate;

}
