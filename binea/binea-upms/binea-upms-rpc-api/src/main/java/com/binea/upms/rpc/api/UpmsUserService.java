package com.binea.upms.rpc.api;

import com.binea.common.base.BaseService;
import com.binea.upms.dao.model.UpmsUser;
import com.binea.upms.dao.model.UpmsUserExample;

/**
* UpmsUserService接口
* Created by binea on 2017/3/20.
*/
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

}