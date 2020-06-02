package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.ConferenceTag;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/23 17:04
 */
@Repository
public interface ConferenceTagRepository extends JpaRepository<ConferenceTag,String> {

    List<ConferenceTag> findAllByTagIdIn(List<String> tagIdList);

}
