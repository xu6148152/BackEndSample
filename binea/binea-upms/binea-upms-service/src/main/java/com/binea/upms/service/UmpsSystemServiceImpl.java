package com.binea.upms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by binea
 * Date: 25/1/2018
 * TIME: 10:39 PM
 */
@Service
@Transactional
public class UmpsSystemServiceImpl implements UmpsSystemService {
    @Override
    public int deleteByPrimaryKeys(String ids) {
        return 0;
    }

    @Override
    public Object getMapper() {
        return null;
    }
}
