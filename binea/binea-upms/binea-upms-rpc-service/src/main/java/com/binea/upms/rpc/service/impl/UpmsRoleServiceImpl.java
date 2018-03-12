package com.binea.upms.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.upms.dao.mapper.UpmsRoleMapper;
import com.binea.upms.dao.model.UpmsRole;
import com.binea.upms.dao.model.UpmsRoleExample;
import com.binea.upms.rpc.api.UpmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by binea
 * Date: 12/3/2018
 * TIME: 10:14 PM
 */
@Service
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {
    private static Logger _log = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

}
