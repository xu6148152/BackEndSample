package com.binea.springboot;

import com.binea.springboot.service.Properties;
import com.binea.springboot.web.HelloWorldController;
import com.binea.springboot.web.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class, SpringbootApplication.class})
public class SpringbootApplicationTests {

    private static final Log log = LogFactory.getLog(SpringbootApplicationTests.class);

    private MockMvc mvc;

    @Autowired
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController(), new UserController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON)).andExpect
                (status().isOk()).andExpect(content().string(
                equalTo("Hello, world")));
    }

    @Test
    public void test1() throws Exception {
        Assert.assertEquals("binea", properties.getName());
//
        log.info("random long: " + properties.getBigNumber());
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder request = null;
        //test get
        request = get("/users/");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

        //post user
        request = post("/users/").param("id", "1").param("name", "binea").
                param("age", "27");
        mvc.perform(request).andDo(MockMvcResultHandlers.print()).andExpect(content().string(equalTo("success")));

        //test get again
        request = get("/users/");
        mvc.perform(request).andExpect(status().isOk()).andExpect(
                content().string(equalTo("[{\"id\":1,\"name\":\"binea\",\"age\":27}]")));

        //test put
        request = put("/users/1").param("name", "xu").param("age", "20");
        mvc.perform(request).andExpect(content().string(equalTo("success")));

        //test get
        request = get("/users/1");
        mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"xu\",\"age\":20}")));

        //test delete
        request = delete("/users/1");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("success")));

        //test get again
        request = get("/users/");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
    }

}
