package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsTopicMapper;
import com.binea.cms.dao.model.CmsTopic;
import com.binea.cms.dao.model.CmsTopicExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsTopicService接口
* Created by binea on 2017/4/5.
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

}
