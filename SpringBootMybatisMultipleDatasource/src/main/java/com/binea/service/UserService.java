package com.binea.service;

import com.binea.domain.User;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 6:18 PM
 */
public interface UserService {
    User findByName(String userName);
}
