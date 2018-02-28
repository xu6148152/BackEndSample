package com.binea.upms.rpc.api;


import com.binea.common.base.BaseService;
import com.binea.upms.dao.model.UpmsUserRole;
import com.binea.upms.dao.model.UpmsUserRoleExample;

/**
* UpmsUserRoleService接口
* Created by binea on 2017/3/20.
*/
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}