package com.mygradleproject.publictransportation.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mygradleproject.publictransportation.response.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e){

    	BaseResponse apiResponse = new BaseResponse();
        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }



}