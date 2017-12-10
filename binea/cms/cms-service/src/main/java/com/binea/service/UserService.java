package com.binea.service;

import com.binea.cms.mapper.UserMapper;
import com.binea.model.UserVO;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:29 PM
 */
public interface UserService extends BaseService<UserMapper> {

    /**
     * 获取带book数据的用户
     *
     * @param id
     * @return
     */
    UserVO selectUserWithBook(int id);
}
