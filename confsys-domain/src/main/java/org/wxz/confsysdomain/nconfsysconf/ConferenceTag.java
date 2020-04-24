package org.wxz.confsysdomain.nconfsysconf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xingze Wang
 * @create 2020/4/23 16:55
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceTag {

    @GeneratedValue
    private int id;

    @Id
    private String ConferenceTagId;

    private String confId;

    private String tagId;

}
