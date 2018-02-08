package com.binea.cms.rpc.service.impl;

import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.cms.dao.mapper.CmsCategoryTagMapper;
import com.binea.cms.dao.model.CmsCategoryTag;
import com.binea.cms.dao.model.CmsCategoryTagExample;
import com.binea.cms.rpc.api.CmsCategoryTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCategoryTagService实现
* Created by binea on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsCategoryTagServiceImpl extends BaseServiceImpl<CmsCategoryTagMapper, CmsCategoryTag, CmsCategoryTagExample> implements CmsCategoryTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCategoryTagServiceImpl.class);

    @Autowired
    CmsCategoryTagMapper cmsCategoryTagMapper;

}