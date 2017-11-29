package com.binea.mapper;

import com.binea.model.User;
import com.binea.model.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:25 PM
 */

@Repository
public interface UserVOMapper extends UserMapper {

    UserVO selectUserWithBook(int id);

    List<User> selectAll(Map<String, Object> map);

    void insertAutoKey(User user);

}

