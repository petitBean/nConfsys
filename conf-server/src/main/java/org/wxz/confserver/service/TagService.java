package org.wxz.confserver.service;

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

}
