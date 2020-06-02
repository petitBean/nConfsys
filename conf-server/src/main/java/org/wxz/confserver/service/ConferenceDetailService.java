package org.wxz.confserver.service;

import org.wxz.confserver.from.CreateConfDetailFrom;
import org.wxz.confserver.vo.DetailPageVo;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.nconfsysconf.ConferenceDetail;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:33
 */
public interface ConferenceDetailService {

    ConferenceDetail saveOne(ConferenceDetail detail) throws Exception;

    ConferenceDetail findOneByDetailId(String detailId);


    List<ConferenceDetail> findAllByConfDetailIdIn(List<String> idList);

    DetailPageVo getConfDetailPageVo(String confId);

    ConferenceDetail createOneDetail(CreateConfDetailFrom detailFrom) throws Exception;

}
