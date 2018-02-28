package com.binea.upms.rpc.api;

import com.binea.common.base.BaseServiceMock;
import com.binea.upms.dao.mapper.UpmsLogMapper;
import com.binea.upms.dao.model.UpmsLog;
import com.binea.upms.dao.model.UpmsLogExample;

/**
* 降级实现UpmsLogService接口
* Created by binea on 2017/3/20.
*/
public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

}
