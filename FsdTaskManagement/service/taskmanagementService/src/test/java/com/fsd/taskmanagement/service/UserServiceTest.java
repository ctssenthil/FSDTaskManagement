package com.fsd.taskmanagement.service;


import com.fsd.taskmanagement.app.model.User;
import com.fsd.taskmanagement.app.service.UserService;
import com.fsd.taskmanagement.config.DatabaseTestConfig;
import com.fsd.taskmanagement.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DatabaseTestConfig.class, TestConfig.class })
@SpringBootTest()
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        //GIVEN
        User user = new User();
        user.setEmpId(100L);
        user.setFirstName("Maran");
        user.setLastName("R");
        user.setDeleted(false);
        user.setUserId(11L);
        //WHEN
        User resultUser = userService.addUser(user);

        //THEN
        assertThat(resultUser, is(notNullValue()));
        assertThat(resultUser.getFirstName(), is(user.getFirstName()));
        assertThat(resultUser.getLastName(), is(user.getLastName()));
    }

    @Test
    public void testUpdateUser() {
        //GIVEN
        User user = new User();
        user.setEmpId(120L);
        user.setFirstName("Maran");
        user.setLastName("R");
        user.setDeleted(false);
        //WHEN
        userService.addUser(user);
        //GIVEN
        User user2 = userService.findUser(2L);


        user2.setEmpId(100L);
        user2.setFirstName("MaranKUMAR1");
        user2.setLastName("R");
        user2.setDeleted(false);
        //WHEN
        User resultUser = userService.addUser(user2);

        //THEN
        assertThat(resultUser, is(notNullValue()));
        assertThat(resultUser.getFirstName(), is(user2.getFirstName()));
        assertThat(resultUser.getLastName(), is(user2.getLastName()));
    }

    @Test
    public void testGetAllUser() {
        List<User> allUser = userService.findAllUser();
        assertThat(allUser, is(notNullValue()));
    }

    @Test
    public void testDeleteUser() {
        //GIVEN
        User user = new User();
        user.setEmpId(100L);
        user.setFirstName("Maran");
        user.setLastName("R");
        user.setDeleted(true);
        //WHEN
        User resultUser = userService.deleteUser(1L);

        //THEN
        assertThat(resultUser, is(notNullValue()));
        assertThat(resultUser.isDeleted(), is(true));

    }
}
