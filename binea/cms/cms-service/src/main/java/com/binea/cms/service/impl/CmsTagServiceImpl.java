package com.binea.cms.service.impl;

import com.binea.cms.dao.mapper.CmsTagMapper;
import com.binea.cms.service.CmsTagService;
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
public class CmsTagServiceImpl implements CmsTagService {

	private static Logger _log = LoggerFactory.getLogger(CmsTagServiceImpl.class);

	@Autowired
    private CmsTagMapper cmsTagMapper;

	@Override
	public CmsTagMapper getMapper() {
		return cmsTagMapper;
	}

}
