package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.ConferenceDetail;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 18:18
 */
@Repository
public interface ConferenceDetailRepository extends JpaRepository<ConferenceDetail,String> {

    ConferenceDetail findByConfDetailId(String detailId);

    List<ConferenceDetail> findAllByConfDetailIdIn(List<String> idList);

}
