package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsSystemMapper;
import com.binea.cms.dao.model.CmsSystem;
import com.binea.cms.dao.model.CmsSystemExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsSystemService接口
* Created by binea on 2017/4/5.
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

}
