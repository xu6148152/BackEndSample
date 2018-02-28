package com.binea.upms.rpc.api;

import com.binea.common.base.BaseServiceMock;
import com.binea.upms.dao.mapper.UpmsSystemMapper;
import com.binea.upms.dao.model.UpmsSystem;
import com.binea.upms.dao.model.UpmsSystemExample;

/**
* 降级实现UpmsSystemService接口
* Created by binea on 2017/3/20.
*/
public class UpmsSystemServiceMock extends BaseServiceMock<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    @Override
    public UpmsSystem selectUpmsSystemByName(String name) {
        return null;
    }

}
