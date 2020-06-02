package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.Tag;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/23 16:57
 */
@Repository
public interface TagRepository  extends JpaRepository<Tag,String> {

    List<Tag> findAllByTagNameIn(List<String> nameList);

    boolean deleteAllByTagNameIn(List<String> tagNameList);

    boolean deleteByTagName(String tagName);


}

