package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsArticleTagMapper;
import com.binea.cms.dao.model.CmsArticleTag;
import com.binea.cms.dao.model.CmsArticleTagExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsArticleTagService接口
* Created by binea on 2017/4/5.
*/
public class CmsArticleTagServiceMock extends BaseServiceMock<CmsArticleTagMapper, CmsArticleTag, CmsArticleTagExample> implements CmsArticleTagService {

}
