package org.wxz.confserver.service;

import org.wxz.confserver.from.ViewDemandFrom;
import org.wxz.confsysdomain.paper.Solicite;

import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/5/6 21:34
 */
public interface SoliciteService {

    Solicite findOneByConfId(String confId) throws Exception;

    Solicite createOne(String confId,String demand,Date startDate,Date endDate)throws Exception;

    Solicite addViewDemand(ViewDemandFrom from)throws Exception;
}
