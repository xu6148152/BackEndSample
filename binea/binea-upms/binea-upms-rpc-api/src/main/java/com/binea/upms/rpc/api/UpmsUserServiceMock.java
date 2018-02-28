package com.binea.upms.rpc.api;

import com.binea.common.base.BaseServiceMock;
import com.binea.upms.dao.mapper.UpmsUserMapper;
import com.binea.upms.dao.model.UpmsUser;
import com.binea.upms.dao.model.UpmsUserExample;

/**
* 降级实现UpmsUserService接口
* Created by binea on 2017/3/20.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }

}
