package com.mec.apportfoliobackend.project;

import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.exception.ProjectNotFoundException;

import java.util.List;

public interface IProjectService {
    List<ProjectResponse> findAllByPersonId(String personId) throws PersonNotFoundException;
    ProjectResponse save(ProjectRequest academicRequest, String personId) throws PersonNotFoundException;
    ProjectResponse update(ProjectRequest academicRequest, String academicId) throws ProjectNotFoundException;
    void delete(String projectId) throws ProjectNotFoundException;
}
