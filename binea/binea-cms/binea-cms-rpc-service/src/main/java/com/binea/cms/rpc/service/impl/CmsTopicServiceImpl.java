package com.binea.cms.rpc.service.impl;

import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.cms.dao.mapper.CmsTopicMapper;
import com.binea.cms.dao.model.CmsTopic;
import com.binea.cms.dao.model.CmsTopicExample;
import com.binea.cms.rpc.api.CmsTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsTopicService实现
* Created by binea on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsTopicServiceImpl extends BaseServiceImpl<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsTopicServiceImpl.class);

    @Autowired
    CmsTopicMapper cmsTopicMapper;

}