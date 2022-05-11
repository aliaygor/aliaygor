package com.mygradleproject.publictransportation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.service.impl.LoginServiceImpl;

@RestController
@RequestMapping("/users")
public class LoginController {
	
	@Autowired
	private LoginServiceImpl loginService;

	@PostMapping("/login")
    public ResponseEntity<BaseResponse> login(
    		@RequestParam String email,
    		@RequestParam String password){

        BaseResponse apiResponse = loginService.login(email, password);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
	
}
