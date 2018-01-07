package com.binea;

import com.binea.async.AsyncTask;
import com.binea.rabbit.Sender;
import com.binea.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringWebApplication.class})
public class SpringWebApplicationTests {

//    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebApplicationTests.class);

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;

//    @Autowired
//    private RedisTemplate<String, User> redisUserTemplate;

    @Autowired
    private StringRedisTemplate redisStringTemplate;

    @Autowired
    private AsyncTask task;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Sender sender;

    @Before
    public void setUp() {
        userService.deleteAllUsers();
    }

    @Test
    public void testJdbcTemplate() throws Exception {
        userService.create("a", 1);
        userService.create("b", 2);
        userService.create("c", 3);
        userService.create("d", 4);
        userService.create("e", 5);

        assertEquals(5, userService.getAllUsersCount().intValue());

        userService.deleteByName("a");

        userService.deleteByName("b");

        assertEquals(3, userService.getAllUsersCount().intValue());
    }

    @Test
    public void testJpa() throws Exception {

//        userRepository.save(new User("AAA", 10));
//        userRepository.save(new User("BBB", 20));
//        userRepository.save(new User("CCC", 30));
//        userRepository.save(new User("DDD", 40));
//        userRepository.save(new User("EEE", 50));
//        userRepository.save(new User("FFF", 60));
//        userRepository.save(new User("GGG", 70));
//        userRepository.save(new User("HHH", 80));
//        userRepository.save(new User("III", 90));
//        userRepository.save(new User("JJJ", 100));
//
//        assertEquals(10, userRepository.findAll().size());
//
//        assertEquals(60, userRepository.findByName("FFF").getAge().longValue());
//
//        assertEquals(60, userRepository.findUser("FFF").getAge().longValue());
//
//        assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());
//
//        userRepository.delete(userRepository.findByName("AAA"));
//
//        assertEquals(9, userRepository.findAll().size());

    }

    @Test
    public void testRedis() {
//        redisStringTemplate.opsForValue().set("aaa", "111");
//        assertEquals("111", redisStringTemplate.opsForValue().get("aaa"));
//
//        User user = new User(1L, "binea1", 20);
//        redisUserTemplate.opsForValue().set(user.getName(), user);
//
//        user = new User(1L, "binea2", 21);
//        redisUserTemplate.opsForValue().set(user.getName(), user);
//
//        user = new User(1L, "binea3", 22);
//        redisUserTemplate.opsForValue().set(user.getName(), user);
//
//        assertEquals(20, redisUserTemplate.opsForValue().get("binea1").getAge().intValue());
//        assertEquals(21, redisUserTemplate.opsForValue().get("binea2").getAge().intValue());
//        assertEquals(22, redisUserTemplate.opsForValue().get("binea3").getAge().intValue());
    }

    @Test
    public void testMongodb() throws Exception {
//        userRepository.save(new User(1L, "binea1", 20));
//        userRepository.save(new User(2L, "binea2", 21));
//        userRepository.save(new User(3L, "binea3", 22));
//
//        assertEquals(3, userRepository.findAll().size());
//
//        User u = userRepository.findOne(1L);
//        userRepository.delete(u);
//        assertEquals(2, userRepository.findAll().size());
//
//        u = userRepository.findByName("binea2");
//        userRepository.delete(u);
//        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void testAsyncTask() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();

        System.out.println("all tasks have done, elapsed time: " + (end - start) + " ms");
    }

    @Test
    public void testLog4jConfig() {
//        LOGGER.info("output info");
//        LOGGER.debug("output debug");
//        LOGGER.error("output error");
    }

    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("xubinggui@qq.com");
        simpleMailMessage.setTo("xubinggui@qq.com");
        simpleMailMessage.setSubject("Test");
        simpleMailMessage.setText("测试邮件");

        mailSender.send(simpleMailMessage);
    }

    @Test
    public void testRabbitMQ() throws Exception {
        sender.send();
    }
}
