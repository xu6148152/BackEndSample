package com.binea.upms.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.upms.dao.mapper.UpmsPermissionMapper;
import com.binea.upms.dao.model.UpmsPermission;
import com.binea.upms.dao.model.UpmsPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by binea
 * Date: 12/3/2018
 * TIME: 10:13 PM
 */
@Service
@Transactional
@BaseService
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> {
    private static Logger _log = LoggerFactory.getLogger(UpmsPermissionServiceImpl.class);

    @Autowired
    UpmsPermissionMapper upmsPermissionMapper;
}
