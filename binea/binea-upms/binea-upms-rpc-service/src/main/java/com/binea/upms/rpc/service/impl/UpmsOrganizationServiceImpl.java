package com.binea.upms.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.upms.dao.mapper.UpmsOrganizationMapper;
import com.binea.upms.dao.model.UpmsOrganization;
import com.binea.upms.dao.model.UpmsOrganizationExample;
import com.binea.upms.rpc.api.UpmsOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

}