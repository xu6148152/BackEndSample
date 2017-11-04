package com.binea.repository;

import com.binea.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 6:43 PM
 */
public interface UserRepository extends MongoRepository<User, Long>{
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    User findUser(String name);
}
