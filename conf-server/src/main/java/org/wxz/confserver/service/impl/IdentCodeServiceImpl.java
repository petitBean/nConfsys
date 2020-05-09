package org.wxz.confserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.repository.IdentCodeRepository;
import org.wxz.confserver.service.IdentCodeService;
import org.wxz.confsysdomain.common.IdentCode;

import javax.transaction.Transactional;

/**
 * @Author xingze Wang
 * @create 2020/5/2 16:15
 */
@Service
public class IdentCodeServiceImpl implements IdentCodeService {

    @Autowired
    private IdentCodeRepository identCodeRepository;

    @Override
    public IdentCode findOneByUserName(String userName) {
        return identCodeRepository.findByUserName(userName);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public IdentCode saveOne(IdentCode identCode) throws Exception {
        return identCodeRepository.save(identCode);
    }

    @Override
    public void deleteOneById(String id) {
        identCodeRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUserName(String userName) {
        identCodeRepository.deleteAllByUserName(userName);
    }
}
