package com.fsd.taskmanagement.controller;

import com.fsd.taskmanagement.app.controller.TaskController;
import com.fsd.taskmanagement.app.model.ParentTask;
import com.fsd.taskmanagement.app.model.Task;
import com.fsd.taskmanagement.config.DatabaseTestConfig;
import com.fsd.taskmanagement.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DatabaseTestConfig.class, TestConfig.class })
@SpringBootTest()
public class TaskControllerTest
{
    @Autowired
    private TaskController taskController;

    @Test
    public void testAddParentTask()
    {
        //GIVEN
        ParentTask parent= new ParentTask();
        parent.setParentTask("Parent Task");

        //WHEN
        ParentTask parentTask = taskController.saveParentTask(parent);

        //THEN
        assertThat(parentTask, is(notNullValue()));
        assertThat(parentTask.getParentTaskId(), is(notNullValue()));
        assertThat(parentTask.getParentTask(), is(parent.getParentTask()));
    }



    @Test
    public void testAddTaskWithoutParent()
    {
        Task task = new Task();
        task.setTask("Task desc");
        task.setPriority(25);
        task.setStartDate(new Date());
        task.setIsparentTask(false);

        //WHEN
        Task createdTask = taskController.saveTask(task);

        //THEN
        assertThat(createdTask, is(notNullValue()));
        assertThat(createdTask.getTaskId(), is(notNullValue()));
        assertThat(createdTask.getParentTask(), is(nullValue()));

        assertThat(createdTask.getTask(), is(task.getTask()));
        assertThat(createdTask.getStartDate(), is(notNullValue()));
        assertThat(createdTask.getEndDate(), is(nullValue()));
    }

    @Test
    public void testcloseTask()
    {
        Task task = new Task();
        task.setTask("Close task desc");
        task.setPriority(15);
        task.setStartDate(new Date());
        task.setIsparentTask(false);
        Task createdTask = taskController.saveTask(task);
        assertThat(createdTask.getEndDate(), is(nullValue()));
        //WHEN
        Task closedTask = taskController.closeTask(createdTask);

        //THEN
        assertThat(closedTask, is(notNullValue()));
        assertThat(closedTask.getTaskId(), is(createdTask.getTaskId()));
        assertThat(closedTask.getEndDate(), is(notNullValue()));

        assertThat(createdTask.getTask(), is(task.getTask()));
        assertThat(createdTask.getStartDate(), is(notNullValue()));
        assertThat(createdTask.getEndDate(), is(nullValue()));
    }

    @Test
    public void testUpdateTask()
    {
        Task task = new Task();
        task.setTask("Initial Task");
        task.setPriority(15);
        task.setStartDate(new Date());
        task.setIsparentTask(false);
        Task createdTask = taskController.saveTask(task);
        createdTask.setTask("Initial Task Updated");
        //WHEN
        taskController.updateTask(createdTask);

        //THEN
        Task updatedTask = taskController.getTaskByID(createdTask.getTaskId());
        assertThat(updatedTask, is(notNullValue()));
        assertThat(updatedTask.getTaskId(), is(createdTask.getTaskId()));
        assertThat(updatedTask.getTask(), is("Initial Task Updated"));

        assertThat(createdTask.getTask(), is(task.getTask()));
        assertThat(createdTask.getStartDate(), is(notNullValue()));
        assertThat(createdTask.getEndDate(), is(nullValue()));
    }
}
