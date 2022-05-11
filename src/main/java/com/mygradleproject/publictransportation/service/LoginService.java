package com.mygradleproject.publictransportation.service;

import com.mygradleproject.publictransportation.response.BaseResponse;

public interface LoginService {

	public BaseResponse login(String email, String password);

}
