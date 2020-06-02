package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.TagFrom;
import org.wxz.confserver.repository.TagRepository;
import org.wxz.confserver.service.TagService;
import org.wxz.confsysdomain.nconfsysconf.Tag;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
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

    @Override
    public boolean deleteAllByNameIn(List<String> nameList) throws Exception {
        return tagRepository.deleteAllByTagNameIn(nameList);
    }

    @Override
    public boolean deleteOneByName(String name) throws Exception {
        return tagRepository.deleteByTagName(name);
    }

    @Override
    public Tag createOne(TagFrom from) throws Exception {
        if (from.getName()==null||from.getDescription()==null){
            log.error("创建标签-失败-参数错误");
            throw  new ConfException("错误的请求");
        }
        List<Tag> list=findAllTagNameIn(Collections.singletonList(from.getName()));
        if (list!=null&&list.size()!=1){
            log.error("创建标签-失败-已经存在");
            throw  new ConfException("已存在");
        }
        Tag tag=new Tag();
        tag.setTagName(from.getName());
        tag.setTagDescription(from.getDescription());
        return saveOne(tag);
    }
}
