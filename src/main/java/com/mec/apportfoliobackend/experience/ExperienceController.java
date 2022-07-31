package com.mec.apportfoliobackend.experience;

import com.mec.apportfoliobackend.exception.ExperienceNotFoundException;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/experience")
public class ExperienceController {
    private final IExperienceService experienceService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ExperienceController.class);

    public ExperienceController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Operation(summary = "Display experience data", description = "Display personal experience from person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @GetMapping(value = "/public/{idPerson}", produces = {"application/json"})
    public ResponseEntity<List<ExperienceResponse>> displayAcademic(@PathVariable String idPerson) throws PersonNotFoundException {
        return new ResponseEntity<>(experienceService.findAllByPersonId(idPerson), HttpStatus.OK);
    }
    @Operation(summary = "Create experience", description = "Create experience from a person", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PostMapping(value = "/{idPerson}", produces = {"application/json"})
    public ResponseEntity<ExperienceResponse> saveAcademic(@PathVariable String idPerson, @Valid @RequestBody ExperienceRequest experienceRequest) throws PersonNotFoundException {
        return new ResponseEntity<>(experienceService.save(experienceRequest, idPerson), HttpStatus.CREATED);
    }
    @Operation(summary = "Update experience", description = "Update experience from a person", responses = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<ExperienceResponse> updateAcademic(@PathVariable String id, @Valid @RequestBody ExperienceRequest experienceRequest) throws ExperienceNotFoundException {
        return new ResponseEntity<>(experienceService.update(experienceRequest,id), HttpStatus.OK);
    }
    @Operation(summary = "Delete experience", description = "Delete experience from a person", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteAcademic(@PathVariable String id) throws ExperienceNotFoundException {
        experienceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
