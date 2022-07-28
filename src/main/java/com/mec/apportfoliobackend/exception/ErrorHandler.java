package com.mec.apportfoliobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpServletRequest request,
                                                             HttpMessageNotReadableException exception) {
        ErrorInfo errorInfo = new ErrorInfo(Objects.requireNonNull(exception.getMessage()).substring(0, 16), HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<?> notReachMinimumLengthException(HttpServletRequest request,
                                                            PersonNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AcademicNotFoundException.class)
    public ResponseEntity<?> academicNotFoundException(HttpServletRequest request,
                                                            AcademicNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ExperienceNotFoundException.class)
    public ResponseEntity<?> experienceNotFoundException(HttpServletRequest request,
                                                       ExperienceNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = SkillNotFoundException.class)
    public ResponseEntity<?> skillNotFoundException(HttpServletRequest request,
                                                       SkillNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
