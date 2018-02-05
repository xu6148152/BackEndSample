package com.binea.upms.rpc.api;

import com.binea.common.base.BaseService;
import com.binea.upms.dao.mapper.UpmsSystemMapper;
import com.binea.upms.dao.model.UpmsSystem;
import com.binea.upms.dao.model.UpmsSystemExample;

/**
 * Created by binea
 * Date: 29/1/2018
 * TIME: 10:21 PM
 */
public interface UpmsSystemService extends BaseService<UpmsSystemMapper, UpmsSystemExample> {
    int deleteByPrimaryKeys(String ids);
}
