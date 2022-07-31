package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.project.Project;
import com.mec.apportfoliobackend.project.ProjectRequest;
import com.mec.apportfoliobackend.project.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {
    public static List<ProjectResponse> projectListToProjectResponseList(java.util.List<Project> projectList) {
        List<com.mec.apportfoliobackend.project.ProjectResponse> projectResponseList = new ArrayList<>();
        for (Project project : projectList) {
            com.mec.apportfoliobackend.project.ProjectResponse projectResponse = createProjectResponse(project);
            projectResponseList.add(projectResponse);
        }
        return projectResponseList;
    }

    public static ProjectResponse createProjectResponse(Project project) {
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(project.getId().toString());
        projectResponse.setImageUrl(project.getImageUrl());
        projectResponse.setName(project.getName());
        projectResponse.setDate(project.getDate());
        projectResponse.setUrl(project.getUrl());
        projectResponse.setDescription(project.getDescription());
        projectResponse.setPersonId(project.getPerson().getId().toString());
        return projectResponse;
    }

    public static Project projectRequestToProject(ProjectRequest projectRequest) {
        Project project = new Project();
        updateProjectData(project, projectRequest);
        return project;
    }

    public static void updateProjectData(Project project, ProjectRequest projectRequest) {
        project.setImageUrl(projectRequest.getImageUrl());
        project.setName(projectRequest.getName());
        project.setDate(projectRequest.getDate());
        project.setUrl(projectRequest.getUrl());
        project.setDescription(projectRequest.getDescription());
    }

    public static ProjectResponse projectToProjectResponse(Project project) {
        return createProjectResponse(project);
    }
}
