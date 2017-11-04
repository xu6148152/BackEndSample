package com.binea.web;

import com.binea.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 5:31 PM
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    static Map<Long, User> users = new ConcurrentHashMap<>();

    @ApiOperation(value = "get userList", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @ApiOperation(value = "create user", notes = "Create user accord params")
    @ApiImplicitParam(name = "user", value = "user info", required = true, dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "get user by id", notes = "get user info accord id")
    @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value = "update user info", notes = "update user info by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "user info", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User localUser = users.get(id);
        localUser.setName(user.getName());
        localUser.setAge(user.getAge());
        return "success";
    }

    @ApiOperation(value = "delete user info", notes = "delete user info by id")
    @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
