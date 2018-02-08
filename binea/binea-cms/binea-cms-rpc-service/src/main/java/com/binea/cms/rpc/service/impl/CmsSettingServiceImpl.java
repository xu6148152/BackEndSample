package com.binea.cms.rpc.service.impl;

import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.cms.dao.mapper.CmsSettingMapper;
import com.binea.cms.dao.model.CmsSetting;
import com.binea.cms.dao.model.CmsSettingExample;
import com.binea.cms.rpc.api.CmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsSettingService实现
* Created by binea on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSettingServiceImpl.class);

    @Autowired
    CmsSettingMapper cmsSettingMapper;

}