package com.fsd.taskmanagement.controller;


import com.fsd.taskmanagement.app.controller.UserController;
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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DatabaseTestConfig.class, TestConfig.class })
@SpringBootTest()
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void testAddUser() {
        //GIVEN
        User user = new User();
        user.setEmpId(100L);
        user.setFirstName("MaranK");
        user.setLastName("R");
        user.setDeleted(false);

        //WHEN
        User resultUser = userController.addUser(user);

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
        User user1 = userController.addUser(user);
        //GIVEN
        User resultUser = userController.updateUser(user1);


        //THEN
        assertThat(resultUser, is(notNullValue()));

    }

    @Test
    public void testGetAllUser() {
        List<User> allUser = userController.getAllUser();
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
        User resultUser = userController.deleteUser(1L);

        //THEN
        assertThat(resultUser, is(notNullValue()));
        assertThat(resultUser.isDeleted(), is(true));

    }
}
