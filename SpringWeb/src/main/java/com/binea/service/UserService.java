package com.binea.service;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 6:20 PM
 */
public interface UserService {
    void create(String name, Integer age);

    void deleteByName(String name);

    Integer getAllUsersCount();

    void deleteAllUsers();
}
