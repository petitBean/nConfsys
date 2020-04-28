package org.wxz.confserver.service;

import org.wxz.confserver.from.CreateResourceFrom;
import org.wxz.confsysdomain.nconfsysconf.Resource;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 2:10
 */
public interface ResourceService {

    List<Resource> findListByConfIdAndType(String confId, int type);

    Resource saveOne(Resource resource)throws Exception;

    Resource createOne(CreateResourceFrom resourceFrom) throws Exception;

    Resource findOneByOriginName(String originName);

    List<Resource> findListByOriginNameLike(String key);


}
