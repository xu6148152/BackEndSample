package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsPageMapper;
import com.binea.cms.dao.model.CmsPage;
import com.binea.cms.dao.model.CmsPageExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsPageService接口
* Created by binea on 2017/4/5.
*/
public class CmsPageServiceMock extends BaseServiceMock<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

}
