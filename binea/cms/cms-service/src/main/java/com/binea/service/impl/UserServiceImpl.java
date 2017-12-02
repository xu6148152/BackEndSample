package com.binea.service.impl;

import com.binea.cms.mapper.UserMapper;
import com.binea.mapper.UserVOMapper;
import com.binea.cms.model.User;
import com.binea.model.UserVO;
import com.binea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    public UserVO selectUserWithBook(int id) {
        return userVOMapper.selectUserWithBook(id);
    }

    /**
     * 根据条件获取用户列表
     *
     * @param map
     * @return
     */
    public List<User> selectAll(Map<String, Object> map) {
        return userVOMapper.selectAll(map);
    }

    /**
     * 插入用户并返回主键
     *
     * @param user
     */
    public void insertAutoKey(User user) {
        userVOMapper.insertAutoKey(user);
    }
}
