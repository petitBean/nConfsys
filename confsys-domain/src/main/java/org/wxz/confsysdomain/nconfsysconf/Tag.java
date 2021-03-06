package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:50
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @GeneratedValue
    private int id;

    @Id
    private String tagId;

    private String tagName;

    private String tagDescription;


}
