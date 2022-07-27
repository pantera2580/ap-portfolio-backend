package com.mec.apportfoliobackend.security.user;

import com.mec.apportfoliobackend.security.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AuthService authService;

    @Operation(summary = "Login", description = "Login a user in the api", responses = {
            @ApiResponse(responseCode = "200", description = "Successful login"),
            @ApiResponse(responseCode = "400", description = "Bad User supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not Found", content = @Content)

    })
    @PostMapping(value = "/login", produces = {"application/json"})
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            LOGGER.info("Bad Request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!authService.existsByUsername(loginRequest.getUsername())) {
            LOGGER.info("Username: %s not found".formatted(loginRequest.getUsername()));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoginResponse loginResponse = authService.login(loginRequest);
        LOGGER.info("Successful login");
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
