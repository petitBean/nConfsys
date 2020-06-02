package org.wxz.confserver.service;

import org.wxz.confserver.from.TagFrom;
import org.wxz.confsysdomain.nconfsysconf.Tag;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:54
 */
public interface TagService {

    Tag saveOne(Tag tag) throws Exception;

    List<Tag> findAll();

    List<Tag> findAllTagNameIn(List<String> tagNameList);

    boolean deleteAllByNameIn(List<String> nameList)throws Exception;

    boolean deleteOneByName(String name)throws Exception;

    Tag createOne(TagFrom from)throws Exception;
}
