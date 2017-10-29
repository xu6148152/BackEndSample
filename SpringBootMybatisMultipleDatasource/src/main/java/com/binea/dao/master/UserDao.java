package com.binea.dao.master;

import com.binea.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 6:01 PM
 */
@Mapper
public interface UserDao {
    User findByName(@Param("userName") String userName);
}
