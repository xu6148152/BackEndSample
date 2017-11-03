package com.binea.springboot;

import com.binea.springboot.service.Properties;
import com.binea.springboot.web.HelloWorldController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class, SpringbootApplication.class})
public class SpringbootApplicationTests {

    private static final Log log = LogFactory.getLog(SpringbootApplicationTests.class);

    private MockMvc mvc;

    @Autowired
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect
                (MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(Matchers
                .equalTo("Hello, world")));
    }

    @Test
    public void test1() throws Exception {
        Assert.assertEquals("binea", properties.getName());
//
        log.info("random long: " + properties.getBigNumber());
    }

}
