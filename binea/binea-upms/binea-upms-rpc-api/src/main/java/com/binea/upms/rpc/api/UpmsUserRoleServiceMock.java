package com.binea.upms.rpc.api;


import com.binea.common.base.BaseServiceMock;
import com.binea.upms.dao.mapper.UpmsUserRoleMapper;
import com.binea.upms.dao.model.UpmsUserRole;
import com.binea.upms.dao.model.UpmsUserRoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 降级实现UpmsUserRoleService接口
 * Created by binea on 2017/3/20.
 */
public class UpmsUserRoleServiceMock extends BaseServiceMock<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserRoleServiceMock.class);

    @Override
    public int role(String[] roleIds, int id) {
        LOGGER.info("UpmsUserRoleServiceMock => role");
        return 0;
    }

}
