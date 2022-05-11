package com.mygradleproject.publictransportation.service;

import java.util.List;

import com.mygradleproject.publictransportation.entity.User;
import com.mygradleproject.publictransportation.response.BaseResponse;

public interface UserService {

	public List<User> getAllUsers();
	
	public User getUserById(int userId);
	
	public User addOrUpdateUser(User user);
	
	public User deleteUser(int userId) throws Exception;
	
	public BaseResponse getUsers();
	
}
