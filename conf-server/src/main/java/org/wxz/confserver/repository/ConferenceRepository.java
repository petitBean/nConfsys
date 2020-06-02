package org.wxz.confserver.repository;

import org.hibernate.engine.spi.ExecutableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.Conference;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 18:17
 */
@Repository
public interface ConferenceRepository extends JpaRepository<Conference,String> {

    Conference findByConfId(String conId);

    Conference findByConfName(String confName);

    /*Page<Conference> findAll(Pageable pageable,Sort sort);
*/
    @Query("select conference from Conference conference  where conference.keyWords like %?1%")
    List<Conference> findAllByKeyWordsLike(String keyWords);

    @Query("select conference from Conference conference  where conference.confTopic like %?1%")
    List<Conference> findAllByConfTopicLike(String key);

    @Query("select conference from Conference conference  where conference.confName like %?1%")
    List<Conference> findAllByConfNameLike(String key);

    List<Conference> findAllByConfIdIn(List<String> confIdList);

    List<Conference> findAllByConfIdInAndStatusIn(List<String> confIdList,List<Integer> statusList,Pageable pageable);

    int countAllByConfIdInAndStatusIn(List<String> confIdList,List<Integer> statusList);
}
