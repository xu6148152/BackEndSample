package com.binea.upms.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.upms.dao.mapper.UpmsUserMapper;
import com.binea.upms.dao.model.UpmsUser;
import com.binea.upms.dao.model.UpmsUserExample;
import com.binea.upms.rpc.api.UpmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(upmsUser.getUsername());
        long count = upmsUserMapper.countByExample(upmsUserExample);
        if (count > 0) {
            return null;
        }
        upmsUserMapper.insert(upmsUser);
        return upmsUser;
    }

}