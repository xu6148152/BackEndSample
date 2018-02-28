package com.binea.upms.rpc.api;

import com.binea.common.base.BaseService;
import com.binea.upms.dao.model.UpmsSystem;
import com.binea.upms.dao.model.UpmsSystemExample;

/**
* UpmsSystemService接口
* Created by binea on 2017/3/20.
*/
public interface UpmsSystemService extends BaseService<UpmsSystem, UpmsSystemExample> {

    /**
     * 根据name获取UpmsSystem
     * @param name
     * @return
     */
    UpmsSystem selectUpmsSystemByName(String name);

}