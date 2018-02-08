package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsMenuMapper;
import com.binea.cms.dao.model.CmsMenu;
import com.binea.cms.dao.model.CmsMenuExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsMenuService接口
* Created by binea on 2017/4/5.
*/
public class CmsMenuServiceMock extends BaseServiceMock<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

}
