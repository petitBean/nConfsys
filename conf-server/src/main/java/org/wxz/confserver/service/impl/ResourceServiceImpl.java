package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.CreateResourceFrom;
import org.wxz.confserver.repository.ResourceRepository;
import org.wxz.confserver.service.ResourceService;
import org.wxz.confsysdomain.nconfsysconf.Resource;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 2:17
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> findListByConfIdAndType(String confId, int type) {
        return resourceRepository.findAllByConfIdAndType(confId,type);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Resource saveOne(Resource resource) throws Exception {
        return resourceRepository.save(resource);
    }

    /**
     * 创建资源
     * @param resourceFrom
     * @return
     * @throws Exception
     */
    @Override
    public Resource createOne(CreateResourceFrom resourceFrom) throws Exception {
        Resource resource=new Resource();
        Resource founded=findOneByOriginName(resourceFrom.getOriginName());
        if (founded!=null){
            throw new ConfException("同名文件已存在！");
        }
        BeanUtils.copyProperties(resourceFrom,resource);
        resource.setResourceId(KeyUtil.getUniqueKey());
        resource.setUploadDate(new Date());
        Resource result=saveOne(resource);
        return resource;
    }

    /**
     * 根据文件名查找
     * @param originName
     * @return
     */
    @Override
    public Resource findOneByOriginName(String originName) {
        return resourceRepository.findByOriginName(originName);
    }

    @Override
    public List<Resource> findListByOriginNameLike(String key) {
        return resourceRepository.findAllByOriginNameLike(key);
    }

    
}
