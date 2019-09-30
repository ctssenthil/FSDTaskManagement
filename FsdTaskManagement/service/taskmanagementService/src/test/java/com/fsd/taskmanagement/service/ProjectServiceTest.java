package com.fsd.taskmanagement.service;


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
public class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @Test
    public void testAddProject() {
        //GIVEN
        Project project = new Project();
        project.setProjectName("Java8");
        project.setCompleted(false);
        //WHEN
        Project resultProject = projectService.addProject(project);

        //THEN
        assertThat(resultProject, is(notNullValue()));
        assertThat(resultProject.getProjectName(), is(project.getProjectName()));
    }

    @Test
    public void testUpdateProject() {
        //GIVEN
        Project project = new Project();
        project.setProjectName("Java");
        project.setCompleted(false);
        //WHEN
        projectService.addProject(project);
        //GIVEN
        Project project1 = projectService.findProject(project.getProjectId());


        project1.setProjectName("JavaFilenet");
        project.setCompleted(false);
        //WHEN
        Project resultProject = projectService.addProject(project1);

        //THEN
        assertThat(resultProject, is(notNullValue()));
        assertThat(resultProject.getProjectName(), is(project1.getProjectName()));
    }

    @Test
    public void testGetAllProject() {
        List<Project> allProject = projectService.findAllProject();
        assertThat(allProject, is(notNullValue()));
    }

    @Test
    public void testSuspendedProejct() {
        //GIVEN
        Project project = new Project();
        project.setProjectName("Java");
        project.setCompleted(true);
        projectService.addProject(project);
        //WHEN
        projectService.suspendProject(project.getProjectId());
        //THEN
        Project project1 = projectService.findProject(project.getProjectId());
        assertThat(project1, is(notNullValue()));

    }
}
