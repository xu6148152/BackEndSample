package com.binea.service.impl;

import com.binea.cms.dao.mapper.UserMapper;
import com.binea.mapper.UserVOMapper;
import com.binea.model.UserVO;
import com.binea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:30 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserVOMapper userVOMapper;

    /**
     * 获取基本操作mapper
     *
     * @return
     */
    public UserMapper getMapper() {
        return userMapper;
    }

    /**
     * 获取带book数据的用户
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "ehcache")
    public UserVO selectUserWithBook(int id) {
        return userVOMapper.selectUserWithBook(id);
    }
}
