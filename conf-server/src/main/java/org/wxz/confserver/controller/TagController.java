package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.confserver.service.impl.TagServiceImpl;
import org.wxz.confsysdomain.nconfsysconf.Tag;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/26 18:31
 */
@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/get_all_tags")
    public ConfResponse getAllTags(){
        List<Tag> tagList=tagService.findAll();
        if (tagList==null){
            log.error("获取标签列表-失败-结果为空！");
            return ConfResponse.fail("暂无标签");
        }
        log.error("获取标签列表-成功！");
        return ConfResponse.success(tagList);
    }
}
