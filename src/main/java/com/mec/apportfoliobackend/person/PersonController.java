package com.mec.apportfoliobackend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<?> displayPerson(){
        return null;
    }
    @PostMapping(produces = {"application/json"})
    public ResponseEntity<?> savePerson() {
        return null;
    }
}
