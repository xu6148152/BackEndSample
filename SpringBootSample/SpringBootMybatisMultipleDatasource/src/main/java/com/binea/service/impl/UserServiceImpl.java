package com.binea.service.impl;

import com.binea.dao.master.UserDao;
import com.binea.dao.cluster.CityDao;
import com.binea.domain.City;
import com.binea.domain.User;
import com.binea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 6:19 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao mUserDao;

    @Autowired
    CityDao mCityDao;

    @Override
    public User findByName(String userName) {
        User user = mUserDao.findByName(userName);
        City city = mCityDao.findByName("南安");
        user.setCity(city);
        return user;
    }
}
