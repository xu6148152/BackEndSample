package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsTagMapper;
import com.binea.cms.dao.model.CmsTag;
import com.binea.cms.dao.model.CmsTagExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsTagService接口
* Created by binea on 2017/4/5.
*/
public class CmsTagServiceMock extends BaseServiceMock<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
