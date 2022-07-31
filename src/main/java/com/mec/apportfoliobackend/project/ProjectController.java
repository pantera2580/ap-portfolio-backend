package com.mec.apportfoliobackend.project;

import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.exception.ProjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/v1/project")
public class ProjectController {
    private final IProjectService projectService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Display project data", description = "Display personal project from person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @GetMapping(value = "/public/{idPerson}", produces = {"application/json"})
    public ResponseEntity<List<ProjectResponse>> displayAcademic(@PathVariable String idPerson) throws PersonNotFoundException {
        return new ResponseEntity<>(projectService.findAllByPersonId(idPerson), HttpStatus.OK);
    }
    @Operation(summary = "Create project", description = "Create project from a person", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PostMapping(value = "/{idPerson}", produces = {"application/json"})
    public ResponseEntity<ProjectResponse> saveAcademic(@PathVariable String idPerson, @Valid @RequestBody ProjectRequest projectRequest) throws PersonNotFoundException {
        return new ResponseEntity<>(projectService.save(projectRequest, idPerson), HttpStatus.CREATED);
    }
    @Operation(summary = "Update project", description = "Update project from a person", responses = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<ProjectResponse> updateAcademic(@PathVariable String id, @Valid @RequestBody ProjectRequest projectRequest) throws ProjectNotFoundException {
        return new ResponseEntity<>(projectService.update(projectRequest,id), HttpStatus.OK);
    }
    @Operation(summary = "Delete project", description = "Delete project from a person", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteAcademic(@PathVariable String id) throws ProjectNotFoundException {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
