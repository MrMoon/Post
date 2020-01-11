package com.moon.squad.shared;

import com.moon.squad.model.shared.ErrorDetails;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
public class JsonExceptionHandler {

    @ExceptionHandler (Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(@NotNull Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(new ErrorDetails(new Date(),exception.getMessage() , Arrays.toString(exception.getStackTrace())));
    }
}
