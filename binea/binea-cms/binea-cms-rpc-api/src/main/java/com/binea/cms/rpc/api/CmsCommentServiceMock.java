package com.binea.cms.rpc.api;

import com.binea.cms.dao.mapper.CmsCommentMapper;
import com.binea.cms.dao.model.CmsComment;
import com.binea.cms.dao.model.CmsCommentExample;
import com.binea.common.base.BaseServiceMock;

/**
* 降级实现CmsCommentService接口
* Created by binea on 2017/4/5.
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
