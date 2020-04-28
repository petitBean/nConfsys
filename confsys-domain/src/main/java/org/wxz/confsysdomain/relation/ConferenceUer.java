package org.wxz.confsysdomain.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xingze Wang
 * @create 2020/4/25 1:12
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceUer {

    @GeneratedValue
    private int id;

    @Id
    private String conferenceUserId;

    private String confId;

    private String userName;

    private String roleName;

}
