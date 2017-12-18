package com.binea.cms.service.impl;

import com.binea.cms.dao.mapper.CmsCommentMapper;
import com.binea.cms.service.CmsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by binea
 * Date: 18/12/2017
 * TIME: 10:14 PM
 */

@Service
@Transactional
public class CmsCommentServiceImpl implements CmsCommentService {

    private static Logger _log = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    @Autowired
    private CmsCommentMapper cmsCommentMapper;

    @Override
    public CmsCommentMapper getMapper() {
        return cmsCommentMapper;
    }

}
