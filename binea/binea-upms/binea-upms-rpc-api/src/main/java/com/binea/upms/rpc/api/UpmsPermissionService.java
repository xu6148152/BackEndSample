package com.binea.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.binea.common.base.BaseService;
import com.binea.upms.dao.model.UpmsPermission;
import com.binea.upms.dao.model.UpmsPermissionExample;

/**
 * UpmsPermissionService接口
 * Created by binea on 2017/3/20.
 */
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}