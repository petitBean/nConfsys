package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.TagRepository;
import org.wxz.confserver.service.TagService;
import org.wxz.confsysdomain.nconfsysconf.Tag;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:56
 */
@Slf4j
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Tag saveOne(Tag tag) throws Exception {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }


    @Override
    public List<Tag> findAllTagNameIn(List<String> tagNameList) {
        return tagRepository.findAllByTagNameIn(tagNameList);
    }
}
