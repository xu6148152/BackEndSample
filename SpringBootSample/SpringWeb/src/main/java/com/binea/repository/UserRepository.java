package com.binea.repository;

import com.binea.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 6:43 PM
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository {

    @Cacheable(key = "#p0")
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Cacheable
    User findUser(String name);

    @CachePut(key = "#p0.name")
    User save(User user);
}
