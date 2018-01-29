package com.binea.upms.rpc.service.impl;

import com.binea.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by binea
 * Date: 29/1/2018
 * TIME: 10:24 PM
 */
public class UpmsSystemServiceImpl implements UpmsSystemService {
    private static Logger _log = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Override
    public int deleteByPrimaryKeys(String ids) {
        return 0;
    }

    @Override
    public Object getMapper() {
        return null;
    }

//    @Autowired
//    private UpmsSystemMapper upmsSystemMapper;
//
//    @Override
//    public UpmsSystemMapper getMapper() {
//        return upmsSystemMapper;
//    }
//
//    // 批量删除
//    @Override
//    public int deleteByPrimaryKeys(String ids) {
//        if (StringUtils.isBlank(ids)) {
//            return 0;
//        }
//        String[] idArray = ids.split("-");
//        int count = 0;
//        for (String id : idArray) {
//            if (StringUtils.isBlank(id)) {
//                continue;
//            }
//            try {
//                count += upmsSystemMapper.deleteByPrimaryKey(Integer.parseInt(id));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return 0;
//            }
//        }
//        return count;
//    }
}
