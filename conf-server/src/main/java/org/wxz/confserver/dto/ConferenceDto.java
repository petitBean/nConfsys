package org.wxz.confserver.dto;

import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/25 17:39
 */
@Data
public class ConferenceDto {

    List<Conference> conferenceList;

    int count=0;

}
