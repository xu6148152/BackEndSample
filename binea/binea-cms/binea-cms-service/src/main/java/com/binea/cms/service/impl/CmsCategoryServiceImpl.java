package com.binea.cms.service.impl;

import com.binea.pay.dao.mapper.CmsCategoryMapper;
import com.binea.cms.service.CmsCategoryService;
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
public class CmsCategoryServiceImpl implements CmsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    @Autowired
    private CmsCategoryMapper cmsCategoryMapper;

    @Override
    public CmsCategoryMapper getMapper() {
        return cmsCategoryMapper;
    }

}
