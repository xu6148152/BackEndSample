package com.binea.cms.rpc.service.impl;

import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.cms.dao.mapper.CmsArticleTagMapper;
import com.binea.cms.dao.model.CmsArticleTag;
import com.binea.cms.dao.model.CmsArticleTagExample;
import com.binea.cms.rpc.api.CmsArticleTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsArticleTagService实现
* Created by binea on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsArticleTagServiceImpl extends BaseServiceImpl<CmsArticleTagMapper, CmsArticleTag, CmsArticleTagExample> implements CmsArticleTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsArticleTagServiceImpl.class);

    @Autowired
    CmsArticleTagMapper cmsArticleTagMapper;

}