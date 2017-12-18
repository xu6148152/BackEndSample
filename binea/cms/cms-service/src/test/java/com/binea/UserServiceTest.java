package com.binea;

import com.binea.cms.dao.model.User;
import com.binea.cms.dao.model.UserVO;
import com.binea.cms.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:35 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:applicationContext-jdbc.xml"
})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void index() {
        // 自定义接口调用
        UserVO userVO = userService.selectUserWithBook(1);
        System.out.println(userVO.getBooks().size());
        // 自动生成接口调用
        User user2 = userService.getMapper().selectByPrimaryKey(1);
        System.out.println(user2.getNickname());
    }

}
