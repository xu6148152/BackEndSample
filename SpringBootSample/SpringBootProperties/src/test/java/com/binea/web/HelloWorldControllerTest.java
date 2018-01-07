package com.binea.web;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorldControllerTest {
    @Test
    public void testHelloWorld() {
        Assert.assertEquals("Hello, world", new HelloWorldController().helloWorld());
    }
}
