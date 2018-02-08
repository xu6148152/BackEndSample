package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsCategoryMapper;
import com.binea.cms.dao.model.CmsCategory;
import com.binea.cms.dao.model.CmsCategoryExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsCategoryService接口
* Created by binea on 2017/4/5.
*/
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

}
