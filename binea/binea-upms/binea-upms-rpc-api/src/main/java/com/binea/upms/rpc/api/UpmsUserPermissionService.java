package com.binea.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.binea.common.base.BaseService;
import com.binea.upms.dao.model.UpmsUserPermission;
import com.binea.upms.dao.model.UpmsUserPermissionExample;

/**
* UpmsUserPermissionService接口
* Created by binea on 2017/3/20.
*/
public interface UpmsUserPermissionService extends BaseService<UpmsUserPermission, UpmsUserPermissionExample> {

    /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);

}