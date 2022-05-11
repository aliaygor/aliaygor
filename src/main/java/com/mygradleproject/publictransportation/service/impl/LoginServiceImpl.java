package com.mygradleproject.publictransportation.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.User;
import com.mygradleproject.publictransportation.repository.LogRepository;
import com.mygradleproject.publictransportation.repository.UserRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.service.LoginService;
import com.mygradleproject.publictransportation.utils.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserRepository userRepository;
    @Autowired //hashleyip gnder parametre olarak
    private LogRepository logRepository;
    @Autowired
    private JwtUtil jwtUtils;

    @Override
    public BaseResponse login(String email, String password) {

        BaseResponse response = new BaseResponse();

        User user = userRepository.findUserByEmailAndPassword(email, password);

        if(user == null){
        	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        	response.setAdditionalInfo("User Login Failed");
        	response.setOperationMessage(BaseResponse.UNSUCCESS);
        	return response;
        }

        String token = jwtUtils.generateJwt(user);

        Map<String , Object> data = new HashMap<>();
        data.put("accessToken", token);

        logRepository.insertLog("user is logged in", user.getUserId());
         
        response.setData(data);
        response.setStatus(HttpStatus.OK.value());
    	response.setAdditionalInfo("User Logged In");
    	response.setOperationMessage(BaseResponse.SUCCESS);
        return response;
    }
}
