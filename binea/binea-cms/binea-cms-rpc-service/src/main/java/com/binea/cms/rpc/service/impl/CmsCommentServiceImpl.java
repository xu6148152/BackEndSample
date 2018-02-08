package com.binea.cms.rpc.service.impl;

import com.binea.common.annotation.BaseService;
import com.binea.common.base.BaseServiceImpl;
import com.binea.cms.dao.mapper.CmsCommentMapper;
import com.binea.cms.dao.model.CmsComment;
import com.binea.cms.dao.model.CmsCommentExample;
import com.binea.cms.rpc.api.CmsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCommentService实现
* Created by binea on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    @Autowired
    CmsCommentMapper cmsCommentMapper;

}