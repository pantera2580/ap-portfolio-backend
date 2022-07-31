package com.mec.apportfoliobackend.skills;

import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.exception.SkillNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/skills")
public class SkillsController {
    private final ISkillsService skillsService;
    private final static Logger LOGGER = LoggerFactory.getLogger(SkillsController.class);
    @Autowired
    public SkillsController(ISkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @Operation(summary = "Display skills data", description = "Display skills from person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @GetMapping(value = "/public/{idPerson}", produces = {"application/json"})
    public ResponseEntity<List<SkillsResponse>> displayAcademic(@PathVariable String idPerson) throws PersonNotFoundException {
        return new ResponseEntity<>(skillsService.findAllByPersonId(idPerson), HttpStatus.OK);
    }
    @Operation(summary = "Create skills", description = "Create skills from a person", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PostMapping(value = "/{idPerson}", produces = {"application/json"})
    public ResponseEntity<SkillsResponse> saveAcademic(@PathVariable String idPerson, @Valid @RequestBody SkillsRequest skillsRequest) throws PersonNotFoundException {
        return new ResponseEntity<>(skillsService.save(skillsRequest, idPerson), HttpStatus.CREATED);
    }
    @Operation(summary = "Update skills", description = "Update skills from a person", responses = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<SkillsResponse> updateAcademic(@PathVariable String id, @Valid @RequestBody SkillsRequest skillsRequest) throws SkillNotFoundException {
        return new ResponseEntity<>(skillsService.update(skillsRequest,id), HttpStatus.OK);
    }
    @Operation(summary = "Delete skills", description = "Delete skills from a person", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteAcademic(@PathVariable String id) throws SkillNotFoundException {
        skillsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
