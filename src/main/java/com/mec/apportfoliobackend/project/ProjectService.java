package com.mec.apportfoliobackend.project;

import com.mec.apportfoliobackend.config.ProjectMapper;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.exception.ProjectNotFoundException;
import com.mec.apportfoliobackend.person.IPersonRepository;
import com.mec.apportfoliobackend.person.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService{
    private final IProjectRepository projectRepository;
    private final IPersonRepository personRepository;

    public ProjectService(IProjectRepository projectRepository, IPersonRepository personRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }
    @Override
    public List<ProjectResponse> findAllByPersonId(String personId) throws PersonNotFoundException {
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        List<Project> projectList = projectRepository.findByPerson(person).orElseThrow(()->new PersonNotFoundException("Person not found"));
        return ProjectMapper.projectListToProjectResponseList(projectList);
    }
    @Override
    public ProjectResponse save(ProjectRequest projectRequest, String personId) throws PersonNotFoundException {
        if(!personRepository.existsById(UUID.fromString(personId))) throw new PersonNotFoundException("Person not found");
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        Project project = ProjectMapper.projectRequestToProject(projectRequest);
        project.setPerson(person);
        projectRepository.save(project);
        return ProjectMapper.projectToProjectResponse(project);
    }
    @Override
    public ProjectResponse update(ProjectRequest projectRequest, String projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findById(UUID.fromString(projectId)).orElseThrow(()->new ProjectNotFoundException("Project not found"));
        ProjectMapper.updateProjectData(project, projectRequest);
        projectRepository.save(project);
        return ProjectMapper.projectToProjectResponse(project);
    }
    @Override
    public void delete(String projectId) throws ProjectNotFoundException {
        if(!projectRepository.existsById(UUID.fromString(projectId))) throw new ProjectNotFoundException("Project not found");
        projectRepository.deleteById(UUID.fromString(projectId));
    }
}
