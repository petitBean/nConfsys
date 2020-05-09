package org.wxz.confserver.service;

import org.wxz.confsysdomain.common.IdentCode;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:12
 */
public interface IdentCodeService {

    IdentCode findOneByUserName(String userName);

    IdentCode saveOne(IdentCode identCode) throws Exception;

    void deleteOneById(String id) ;

    void deleteAllByUserName(String userName);

}
