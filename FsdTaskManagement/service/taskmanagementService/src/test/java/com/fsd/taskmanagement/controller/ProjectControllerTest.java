package com.fsd.taskmanagement.controller;


import com.fsd.taskmanagement.app.controller.ProjectController;
import com.fsd.taskmanagement.app.model.Project;
import com.fsd.taskmanagement.app.service.ProjectService;
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
public class ProjectControllerTest {
    @Autowired
    private ProjectController projectController;

    @Test
    public void testAddProject() {
        //GIVEN
        Project project = new Project();
        project.setProjectName("Java");
        project.setCompleted(false);
        //WHEN
        Project resultProject = projectController.addProject(project);

        //THEN
        assertThat(resultProject, is(notNullValue()));
        assertThat(resultProject.getProjectName(), is(project.getProjectName()));
    }

    @Test
    public void testUpdateProject() {
        //
        //GIVEN
        Project project = new Project();
        project.setProjectName("Java");
        project.setCompleted(false);
        //WHEN
        Project project1 = projectController.addProject(project);


        project1.setProjectName("JavaFilenet");
        project1.setCompleted(false);
        //WHEN
        Project resultProject = projectController.updateProject(project1);

        //THEN
        assertThat(resultProject, is(notNullValue()));
        assertThat(resultProject.getProjectName(), is(project1.getProjectName()));
    }

    @Test
    public void testGetAllProject() {
        List<Project> allProject = projectController.getAllProject();
        assertThat(allProject, is(notNullValue()));
    }

    @Test
    public void testSuspendedProejct() {
        //GIVEN
        Project project = new Project();
        project.setProjectName("Java");
        project.setCompleted(true);
        projectController.addProject(project);
        //WHEN
        projectController.suspendProject(project.getProjectId());
        //THEN
        Project project1 = projectController.suspendProject(project.getProjectId());
        assertThat(project1, is(notNullValue()));

    }
}
