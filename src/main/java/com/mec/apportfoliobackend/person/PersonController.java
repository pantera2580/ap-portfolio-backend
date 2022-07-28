package com.mec.apportfoliobackend.person;

import com.mec.apportfoliobackend.exception.PersonNotFoundException;
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

@RestController
@RequestMapping("/v1")
public class PersonController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private IPersonService personService;
    @Operation(summary = "Display person", description = "Display personal data from user", responses = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)
    })
    @GetMapping(produces = {"application/json"}, value = "/{id}")
    public ResponseEntity<?> displayPerson(@PathVariable String id) throws PersonNotFoundException {
        if(!personService.existById(id)) throw new PersonNotFoundException("Person not found");
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }
    @Operation(summary = "Create person", description = "Create personal data from user", responses = {
            @ApiResponse(responseCode = "201", description = "Successful"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not Found", content = @Content)
    })
    @PostMapping(produces = {"application/json"}, value = "/{id}")
    public ResponseEntity<PersonResponse> savePerson(@Valid @RequestBody PersonRequest personRequest, @PathVariable String id) {
        return new ResponseEntity<>(personService.save(personRequest, id), HttpStatus.CREATED);
    }
    @Operation(summary = "Update person", description = "Update personal data from user", responses = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not Found", content = @Content)

    })
    @PutMapping(produces = {"application/json"}, value = "/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@Valid @RequestBody PersonRequest personRequest, @PathVariable String id) throws PersonNotFoundException {
        if(!personService.existById(id)) throw new PersonNotFoundException("Person not found");
        return new ResponseEntity<>(personService.update(personRequest, id), HttpStatus.OK);
    }
}
