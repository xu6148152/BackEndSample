package com.binea.repository;

import com.binea.domain.User;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 6:43 PM
 */
public interface UserRepository {
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    User findUser(String name);
}
