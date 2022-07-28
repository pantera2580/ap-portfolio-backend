package com.mec.apportfoliobackend.academic;

import com.mec.apportfoliobackend.exception.AcademicNotFoundException;
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
@RequestMapping("/v1/academic")
public class AcademicController {
    @Autowired
    private IAcademicService academicService;
    private final static Logger LOGGER = LoggerFactory.getLogger(AcademicController.class);
    @Operation(summary = "Display academic data", description = "Display personal academic data from person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @GetMapping(value = "/{idPerson}", produces = {"application/json"})
    public ResponseEntity<List<AcademicResponse>> displayAcademic(@PathVariable String idPerson) throws PersonNotFoundException {
        return new ResponseEntity<>(academicService.findAllByPersonId(idPerson), HttpStatus.OK);
    }
    @Operation(summary = "Create academic data", description = "Create academic data from a person", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PostMapping(value = "/{idPerson}", produces = {"application/json"})
    public ResponseEntity<AcademicResponse> saveAcademic(@PathVariable String idPerson, @Valid @RequestBody AcademicRequest academicRequest) throws PersonNotFoundException {
        return new ResponseEntity<>(academicService.save(academicRequest, idPerson), HttpStatus.CREATED);
    }
    @Operation(summary = "Update academic data", description = "Update academic data from a person", responses = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<AcademicResponse> updateAcademic(@PathVariable String id, @Valid @RequestBody AcademicRequest academicRequest) throws AcademicNotFoundException {
        return new ResponseEntity<>(academicService.update(academicRequest,id), HttpStatus.OK);
    }
    @Operation(summary = "Delete academic data", description = "Delete academic data from a person", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteAcademic(@PathVariable String id) throws AcademicNotFoundException {
        academicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
