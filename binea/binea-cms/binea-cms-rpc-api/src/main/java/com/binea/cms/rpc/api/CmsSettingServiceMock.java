package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsSettingMapper;
import com.binea.cms.dao.model.CmsSetting;
import com.binea.cms.dao.model.CmsSettingExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsSettingService接口
* Created by binea on 2017/4/5.
*/
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
