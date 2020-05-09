package org.wxz.confserver.service;

import org.wxz.confserver.from.PayCategoryFrom;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;

/**
 * @Author xingze Wang
 * @create 2020/5/9 6:22
 */
public interface PayCategoryService {

    PayCategory saveOne(PayCategory payCategory)throws Exception;

    PayCategory findOneByConfId(String confId)throws Exception;

    PayCategory createOne(PayCategoryFrom from)throws Exception;


}
